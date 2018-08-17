//package com.example.demo.controller;
//
//import com.example.demo.config.AlipayConfig1;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import com.example.demo.util.AlipaySubmit;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.com.example.demo.util.Date;
//import java.com.example.demo.util.HashMap;
//import java.com.example.demo.util.Iterator;
//import java.com.example.demo.util.Map;
//
///**
// * Created by ningcs on 2018/5/29.
// */
//@Controller
//@RequestMapping("/order")
//public class PayController {
//
//    /**
//     * 支付宝支付页面
//     *
//     * @return
//     * @throws IOException
//     */
//    @RequestMapping(value = "/aliPay")
//    public void aliPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
////        LOGGER.info("支付宝支付页面");
//        //商户订单号，商户网站订单系统中唯一订单号，必填
////        String orderNo = request.getParameter("orderNo");
//        String orderNo =new Date().getTime()+"";
//        //订单名称，必填
////        String subjectName = request.getParameter("subjectName");
//        String subjectName = "测试";
//        //付款金额，必填
////        String total_fee = request.getParameter("fee");
//        String total_fee = "200";
//        //商品描述，可空
//        String body = "法海风控 " + subjectName;
//        if ("money".equals(body)) {
//            body = "法海风控  余额充值";
//        }
//        //把请求参数打包成map
//        Map<String, String> sParaTemp = new HashMap<String, String>();
//        sParaTemp.put("service", AlipayConfig1.service);
//        sParaTemp.put("partner", AlipayConfig1.partner);
//        sParaTemp.put("seller_id", AlipayConfig1.seller_id);
//        sParaTemp.put("_input_charset", AlipayConfig1.input_charset);
//        sParaTemp.put("payment_type", AlipayConfig1.payment_type);
//        sParaTemp.put("notify_url", AlipayConfig1.notify_url);
//        sParaTemp.put("return_url", AlipayConfig1.return_url);
//        sParaTemp.put("anti_phishing_key", AlipayConfig1.anti_phishing_key);
//        sParaTemp.put("exter_invoke_ip", AlipayConfig1.exter_invoke_ip);
//        sParaTemp.put("out_trade_no", orderNo);
//        sParaTemp.put("subject", subjectName);
//        sParaTemp.put("total_fee", total_fee);
//        sParaTemp.put("body", body);
//        //其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
//        //如sParaTemp.put("参数名","参数值");
//        //建立请求
//        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        System.out.println(sHtmlText);
//        out.println(sHtmlText);
//
//    }
//
//    /**
//     * 支付宝支付订单
//     * @return
//     * @throws IOException
//     */
//    @ResponseBody
//    @RequestMapping(value = "pay/aliPayOrder", method = RequestMethod.POST)
//    public void aliPayOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {
//////        LOGGER.info("支付订单");
////        //从request中获得参数Map，并返回可读的Map
////        Map<String, String> params = RequestUtil.getParameterMap(request);
//////        LOGGER.info(params.toString());
////        //验证支付宝签名
////        boolean aliSign = AlipayNotify.verify(params);
////        if (aliSign) {//验证成功
////            //交易状态
////            String tradeStatus = params.get("trade_status");
////            //订单编号
////            String orderNo = params.get("out_trade_no");
////            //支付单号
////            String payNo = params.get("trade_no");
////            //支付账号
////            String payAccount = params.get("buyer_email");
////            //支付金额
////            String totalFee = params.get("total_fee");
////            //收款支付宝账号
////            String sellerId = params.get("seller_id");
////            if (Constant.ALIPAY_TRADE_SUCCESS.equals(tradeStatus)) {//支付宝支付状态为成功
////                //验证支付宝返回信息与请求信息一致
////                if (ProInfoUtil.getInstance().getProperty("alipay_partner").equals(sellerId)) {
////                    //订单处理状态
////                    String orderHandleStatus = "error";
////                    //验证订单未做支付处理
////                    Order order = orderService.queryOrderByOrderNo(orderNo);
////                    //订单已支付
////                    if (Constant.DEALSTATUS_PAY.equals(order.getDealStatus())) {
////                        response.getWriter().print("success");
////                        return;
////                    }
////                    if (null != order && Double.parseDouble(totalFee) == order.getDealPrice() &&
////                            Constant.DEALSTATUS_NOT_PAY.equals(order.getDealStatus())) {//验证金额是否和订单一致
////                        //更新订单为已支付、更新用户套餐余额、添加用户充值记录、添加用户余额支出记录
////                        order.setDealStatus(Constant.DEALSTATUS_PAY);
////                        order.setPayNo(payNo);
////                        order.setPayType(Constant.ALIPAY);
////                        order.setPayAccount(payAccount);
////                        try {
////                            //支付成功处理支付业务
////                            boolean result = orderService.payOrder(order);
////                            if (result) {
////                                //成功后向支付宝返回成功标志
////                                LOGGER.info("支付宝支付成功");
////                                orderHandleStatus = "success";
////                                response.getWriter().print("success");
////                            }
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                            LOGGER.info("支付宝支付失败");
////                            response.getWriter().print("fail");
////                        }
////
////                    }
////                    //添加支付信息
////                    Map<String, Object> map = new HashMap<String, Object>();
////                    map.put("params", params.toString());
////                    map.put("payType", Constant.ALIPAY);
////                    map.put("orderNo", orderNo);
////                    map.put("handleStatus", orderHandleStatus);
////                    orderService.addPayInfo(map);
////                }
////            }
////
////        } else {//验证失败
////            LOGGER.info("支付宝返回验证失败");
////            response.getWriter().print("fail");
////        }
//    }
//
//
//    /**
//     * 从request中获得参数Map，并返回可读的Map
//     *
//     * @param request
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public static Map getParameterMap(HttpServletRequest request) {
//        // 参数Map
//        Map properties = request.getParameterMap();
//        // 返回值Map
//        Map<String, String> returnMap = new HashMap<String, String>();
//        Iterator entries = properties.entrySet().iterator();
//        Map.Entry entry;
//        String name = "";
//        String value = "";
//        while (entries.hasNext()) {
//            entry = (Map.Entry) entries.next();
//            name = (String) entry.getKey();
//            Object valueObj = entry.getValue();
//            if(null == valueObj){
//                value = "";
//            }else if(valueObj instanceof String[]){
//                String[] values = (String[])valueObj;
//                for(int i=0;i<values.length;i++){
//                    value = values[i] + ",";
//                }
//                value = value.substring(0, value.length()-1);
//            }else{
//                value = valueObj.toString();
//            }
//            returnMap.put(name, value);
//        }
//        return returnMap;
//    }
//}
