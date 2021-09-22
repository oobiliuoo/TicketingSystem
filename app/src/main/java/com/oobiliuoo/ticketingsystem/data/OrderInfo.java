package com.oobiliuoo.ticketingsystem.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * @author biliu
 * 订单表
 */
public class OrderInfo extends LitePalSupport {

    /**景点信息*/
    @Column(nullable = false)
    private ScenicSpot scenicSpot;

    /**订单号*/
    @Column(nullable = false)
    private String oderID;

    /**下单时间*/
    @Column(nullable = false)
    private String orderTime;
    /**生效日期*/
    @Column(nullable = false)
    private String workTime;
    /**票据类型*/
    @Column(defaultValue = "adult")
    private String orderType;
    /**购票数量*/
    @Column(defaultValue = "1")
    private  Integer orderNumber;
    /**总价*/
    @Column(nullable = false)
    private Integer sumPrice;

    public OrderInfo() {
    }

    public ScenicSpot getScenicSpot() {
        return scenicSpot;
    }

    public void setScenicSpot(ScenicSpot scenicSpot) {
        this.scenicSpot = scenicSpot;
    }

    public String getOderID() {
        return oderID;
    }

    public void setOderID(String oderID) {
        this.oderID = oderID;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Integer sumPrice) {
        this.sumPrice = sumPrice;
    }
}
