package com.example.demo.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ningcs on 2018/5/25.
 */
public class AlipayConfig {


/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */


//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

////    生产环境
//    public static String app_id ="2017071307742497";
//    public static String merchant_private_key ="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCnJTYPKrOSy6HB8RSkWV+SuUKf6XiQWHf7ZeqMudYBIaXUp8Kfws+s8HPXs6u3hLM1484s8Wq1pKi1ypmq0rzVEeIskQHVl9wkjgJx+A8csPqKIdoJ0Nk94pNaY0zIrFdd+3/7eF/bA6MoqH6vxIxplA9Ddg9lfstuMm8140UTZ60Ef/U+h/MOXdG02AVwzBkgCh56vOE2OlAikqal6KeM/m+EUCa9Qv01w2M2vPf4rD1XLUyIaJS14hVU/cZgOt0w0TH85US9RaaUNncsyPKAo+49QzpcKBQkLq23E4NGLJW/hos0BnI73rRI21M+JYOa16k3nu8aLEAMPdkvl7C5AgMBAAECggEAdq4fUmmZlBPuyUyqwX+MMxxESFN4fdWza9gT0NRrNeZ0Xyi3MsUavMsHFhPHS8l3jj4wuQy6HjvKZzpyHi8AUbIfDFHyIX1wnKwQ5GekI1UusEIVFX8roZtt0XJMpPhqGJtpZl80gMh2Y3owahF/OcAdjmMXz5NWUuqQuIylEX8Xd3fBPCf+/IgXFag95t1OMLu4DIYcxL2klrUT7nW99aJE2cARghTtWLCkehnKT+1iY94YYFUa7/nj8zY6hiGqR03jguiwExL84/78N2wWE30b/JhsFomX1J3HlchsWnktRyaOX/LugFLyKT5w/pXEvGAPQeVH3lDlOhzn2OZB4QKBgQDep1nzcvi2RLeFaHV6QgihhnLCHZ4kOKO1rK2qowSYnFig712b2XRYv0QSMCzRaARgHZm+Rpf8AWS7vGCr4XzNRXtjPJlHmIvLx/DamfscELAXT+H8XNbJ0iN6agumj0yHnwhk7Qt83NQ8HOke/8pM4BgtZZwqVdAdl0dKX+DrfQKBgQDALaSfT3bWkdOyg9H+Fr++EnTc7dZ58kZ2nJ1ltLJHoSE+YVb1E4Bk8kRQRfsxl2FKggbL9TpTVohw7UR9OGxPT8GH2GcVRhHBaMZuHibUjuWHJfQaQVi3EfGx7Cbd9rcrpTgnJMmnIpWE21Nx15NoibctvACf2BsF6Qp75hfG7QKBgBqowQ10O7FfQvYXTtGnrG7isUTq1O+TVI7BMT4YLMm1FquWXYf2noatyBHBcOxV3wg/IxR0UWhcJlqQMKcD5bSSWDbGn7fB3SM2OnGCNF3k5aC08OMNnq4lmhfjMKftyoUGlrO26kQ2ELdt4r/Scr+HqX1uCCfNhJC2d1PBbSXlAoGAK/fFXDWACONgPc1u8+UchU4PsqQvl8LYYDbAxA4tY7n3sx/uFzAZBf9vpbX9HIF33uYUzAEVERARM1T1gHiyPwsOShg2rWVUlp10GKmJVMREu0DxsjgVJf1TANP4qHvTuIaabEo8YKLG9ph/ZOJfdWFcqXgD5I460IVawrIH6pkCgYBeauiXWPzknGBaBhB453Xf7S3uN+jfHbD2FTkx9ypuu+/7LqXvzrDa90S8ucCt/qRKfNiNxgEyDnps4AcXJf5lxgfMyL51859NwMkIycq+dL/cMSb3hHx2NvcDNhxzNvOxY5MOn4hyH/KdFVShytSc73zdlD4JfPJOQylLhFxC2g==";
//    public static String alipay_public_key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj7ScRua/x+/aZZKi09mchZ9JHOKJrdworrzyOkIQ2vYJZC6e+SqFwiSt3SL0YeTag0fXwFWgDh2n/brSzYoLkfAe7qmcIGIpmgFQrjRRCvT/Dpr2fUa8OFzBcbEqrabnt7cHZggdtxqoaJqLrjQ7hAW8vWZSuDNSI58R1WdvRk/wQvMhADe01cMQXgV5sHziubP9UQXA18R0PqfepBZWzJVTx40NlaovWJ3vrWLVx9RjfLYfh52E20A4mpFr1505e9xBXdKxHZyEjo5G3JkSdDattGlWR3u/kUzQVRDifWYyJIV/RpzdDaC3m+MYDVQ70ld1LbGqut+j/1IQYB6FmQIDAQAB";

    //    沙箱环境
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016080600179686";
   // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCJU/ZxqDPYPl+/ihQy6zVNab+u4MO+wI8/9X5MAwt/XlYWs68A6PozT2kXr4TVVr8JbyO42Mc/BgjvLqDaROLH1YtDxL7IppkiDShII4PFWiHXFJ4Q4qnGluSWLYmt2RKCJD/KmpwIzZwSXpu08hfexll4icoSeaM3H+/i0FjhwtS5A7Hea7Nm5CLIVOJU+obVGqqU7lonDPrmcVLSIWgNutkKaBYnLAhy5tGWUu7664LOIsIScuvGqXBDuVTF7QYMER23VIAg19oL3fA1c+AyJNd7IeF4ZaifTnlCSWpwsKIt42CoJ+/8E6NehPd5aGh9M90ajSa2Kd6TKyy28GYbAgMBAAECggEAXfV8oLcdAc9rY3nt6AjWJ2k7JUPTqifF21T0XL2YmETLUi0Oh+jfh0vF89a7Y9w+SQ4pusdXS9+tLTkSBUUshN2us2dhwZd7Hrkx3mmuNtE8ZhRNMfBjiOSk8bumf7QN3c0/eDfgmoVTS54/msYpXtsHTFm6UHm5GnZXgzBaH9xU+SQyZZXT6VMV/vViuA1TZvcde4Fu5NnDu7yelMMj2fuPgbZ8NoWcaMkIrLmpzOMjC3e80+iuPXspX2xq28WvPWzZFTfpTLQ+yCmNXncNfcCmO7+7AqoirKpnvTc9A31o7vHdKwAR6wiY7b0e/PnkMF0oHO5e59dESI319h0xgQKBgQDG7FXMK3CYvzeCsTi0uZt+zeKXF6PYted25S+O0HF17riPxmPx84qYGotP8Vjfw4rZpJ14LcMwYIOI8ZgGahE4S00M8yWwA4eYVQ5ixkvFxEpsBht9AratFNB7h65onQpAHYlj0FIlINcG71yw+X7FHRMM5/M6dEDKN9hmxBt3swKBgQCwuzmvBI1u+R2r0RLChGWuEpPPDjghlUVBYavxfVDN6yCPKWZln4v0n/FC8pAPRTA/TIlOZOf9UP5D+kRTG5fHV0hMfthFJjvdzQ1Ff5LpNhiyFsQ39cSxVBDlzFUo3K3wilRYqi1pasYz5aVBvNYJoCedq7xuHhEz9rzKN/aj+QKBgQCsYr7vkm2fUYZvkdlMdNTWDOHigjARa4JNPMv7/ZN12gzDO+/9C0r+CXLVMpgMQk8f0VJNhYrzb5BNquBOHrkRieAZ6tSsNkLZtkwjOSkNF9BO0PQdX66jS7hodOql6gGqEm6vuyaU2mK9tc0j6PL9KvcJZjEYZQqIs+CrHiOXlQKBgQCThAN3CfbADWHt23qsMHTq4ojLyJOjvIPtOgiabr520u7/cFAPPShGDQcFmZATnu3RuCHX9LQLpoJF3W0QbbS36Cvp5erBdVN9JL4SJ5VJ4iSk6gnop9UBKylblr3yeJkbcWtMAuga2MwN336cK3IA4NSQcBu9Uo2LcUVOCX5uiQKBgEXkZU9rvCf1n/AP42rTEi1HI+sL21kMZCewwSkcuUnG1mrqvMnm8QEwwOXX9TZo0pHWTkyZwxpXpixIGIK59pSgmC9yATxnEAgKLZxUQPC3vVt0wnvlMbE3VIvVJyn/zOysgLHNpwzqDREMJQ6B9PZQog4bPetuc9rM/VOJdkt9";
   // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvRKbt9v0HroeUkSEZ3Jvy+O6i/is4mBAg/IB1/9+GfqtbG8GZ/Ql5HFHQfc6TJc2mKWQJ/Y/blgufTctXLxdEtIaUAdbt/iBz6N2vf8SxryrwQF+HEMgzI7Ie4zMBowrOzv+7qtvJn10XSXfB8xrZYOWiKeabKza+ETbujZ2jg2Nle1lso0Otb2tYfGezCGxVVhvCASxW7OSt/AT0UYfkAZqAbj0QLyOD+z5WZsk96ou7BBQsmZDjne+SHQMKiqvMvTLsRBnr79vGmuht037xZbDuz/CPaR8RVO6w098gOOMblFch5NUn6UuoqB0abQCanpKu9k5UHpwQJpev4xCewIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8083/order/notifyUrl.do";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8083/order/returnUrl.do";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
//    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
//    // 支付宝网关沙箱地址
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝日志路径
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
