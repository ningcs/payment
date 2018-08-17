package com.example.demo.service.impl;

import com.example.demo.dto.OrderInfo;
import com.example.demo.service.PayService;
import org.springframework.stereotype.Service;

/**
 * Created by ningcs on 2018/5/30.
 */
@Service
public class PayServiceImpl implements PayService {

    @Override
    public OrderInfo findOneByTradeCode(String orderCode) {
        return null;
    }

    @Override
    public void payOrder(OrderInfo orderInfo) {

    }
}
