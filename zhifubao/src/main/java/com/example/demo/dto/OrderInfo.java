package com.example.demo.dto;

import java.util.Date;

/**
 * Created by ningcs on 2018/5/30.
 */
public class OrderInfo {

    private Integer id;

    private String orderCOde;

    private String totalPrice;

    private String note;

    private Date createTime;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCOde() {
        return orderCOde;
    }

    public void setOrderCOde(String orderCOde) {
        this.orderCOde = orderCOde;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
