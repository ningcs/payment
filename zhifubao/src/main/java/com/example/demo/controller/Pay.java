package com.example.demo.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.demo.config.AlipayConfig;
import com.example.demo.dto.OrderInfo;
import com.example.demo.service.PayService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ningcs on 2018/5/30.
 */
@RestController
@RequestMapping(value = "/order")
public class Pay {
    Log log = LogFactory.getLog(getClass());

    @Autowired
    private PayService payService;
    /**
     * 获取订单数据接口
     * @throws AlipayApiException
     * @throws IOException
     */
    @RequestMapping(value = "/viewOrder",method ={ RequestMethod.POST,RequestMethod.GET})
    public void viewOrder(HttpServletRequest req, Model mod, HttpServletResponse rep) throws AlipayApiException, IOException {


//        //系统下单
//        OrderInfo  param = new OrderInfo();
//        param.setGoodId(goodId);
//        PayService.alipayOrder(cu, param);   //生成订单信息，根据自己项目改动


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new Date().getTime()+"";
        //付款金额，必填
        String total_amount = "0.01";
        //订单名称，必填
        String subject = "测试";
        //商品描述，可空
        String body ="测试";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        rep.setContentType("text/html;charset=" + AlipayConfig.charset);
        rep.getWriter().write(result);//直接将完整的表单html输出到页面
        rep.getWriter().flush();
        rep.getWriter().close();
    }

    /**
     * 回调路径return_url
     * @param request
     * @param response
     * @throws AlipayApiException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/returnUrl",method = {RequestMethod.POST,RequestMethod.GET})
    public String returnUrl(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, UnsupportedEncodingException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,AlipayConfig.sign_type);
        signVerified=true;
        if(signVerified) {
            //商户订单号
            String out_trade_no =request.getParameter("out_trade_no");

            //支付宝交易号
            String trade_no = request.getParameter("trade_no");

            //付款金额
            String total_amount =request.getParameter("total_amount");

            request.setAttribute("out_trade_no", out_trade_no);
            request.setAttribute("trade_no", trade_no);
            request.setAttribute("total_amount", total_amount);

            log.info("订单处理：系统订单号" + out_trade_no + "支付宝交易号：" + trade_no);
            //系统处理根据支付宝回调更改订单状态或者其他关联表的数据
            // TODO: 2018/5/30 获取订单信息
            OrderInfo order = payService.findOneByTradeCode(out_trade_no);
            if(order == null){
                signVerified = false;
                request.setAttribute("signVerified", signVerified);
                request.setAttribute("reason", "商户订单号不存在");
                log.error("系统订单："+ out_trade_no + "不存在。");
            }else{
                if(!order.getTotalPrice().equals(total_amount)){
                    signVerified = false;
                    request.setAttribute("signVerified", signVerified);
                    request.setAttribute("reason", "付款金额不对");
                    return "notify_url";
                }


                if(order.getStatus() == 1){//判断当前订单是否已处理，避免重复处理
                    log.info("系统订单："+ out_trade_no + "无需重复处理。");
                }else{
                    order.setStatus(1);//修改订单状态为已支付
                    Date payedAt = new Date();
                    order.setOrderCOde(trade_no);
                    order.setCreateTime(payedAt);
                    // TODO: 2018/5/30 保存订单信息
                    payService.payOrder(order);
                    log.info("系统订单："+ out_trade_no + "成功支付。");
                }

            }
        }else{
            request.setAttribute("reason", "验签失败");
        }
        request.setAttribute("signVerified", signVerified);
        return "return_url";
    }



    /**
     * 回调路径notify_url
     * @param request
     * @param response
     * @throws AlipayApiException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/notifyUrl",method = {RequestMethod.POST,RequestMethod.GET})
    public String notifyUrl(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, UnsupportedEncodingException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,AlipayConfig.sign_type);
        if(signVerified) {
            //商户订单号
            String out_trade_no =request.getParameter("out_trade_no");

            //支付宝交易号
            String trade_no = request.getParameter("trade_no");

            //付款金额
            String total_amount =request.getParameter("total_amount");

            request.setAttribute("out_trade_no", out_trade_no);
            request.setAttribute("trade_no", trade_no);
            request.setAttribute("total_amount", total_amount);

            log.info("订单处理：系统订单号" + out_trade_no + "支付宝交易号：" + trade_no);
            //系统处理根据支付宝回调更改订单状态或者其他关联表的数据
            // TODO: 2018/5/30 获取订单信息
            OrderInfo order = payService.findOneByTradeCode(out_trade_no);
            if(order == null){
                signVerified = false;
                request.setAttribute("signVerified", signVerified);
                request.setAttribute("reason", "商户订单号不存在");
                log.error("系统订单："+ out_trade_no + "不存在。");
            }else{
                if(!order.getTotalPrice().equals(total_amount)){
                    signVerified = false;
                    request.setAttribute("signVerified", signVerified);
                    request.setAttribute("reason", "付款金额不对");
                    return "notify_url";
                }
            }
        }else{
            request.setAttribute("reason", "验签失败");
        }
        request.setAttribute("signVerified", signVerified);
        return "notify_url";
    }
}
