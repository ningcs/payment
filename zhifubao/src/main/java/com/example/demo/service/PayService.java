package com.example.demo.service;

import com.example.demo.dto.OrderInfo;

/**
 * Created by ningcs on 2018/5/30.
 */
public interface PayService {
    //查找订单信息
     public OrderInfo findOneByTradeCode(String orderCode);
    //保存订单信息
     public void payOrder(OrderInfo orderInfo);
}
