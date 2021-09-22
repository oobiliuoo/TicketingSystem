package com.oobiliuoo.ticketingsystem.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * @author biliu
 * 订单表
 */
public class OrderInfo extends LitePalSupport implements Serializable {

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
    @Column(defaultValue = "成人票")
    private String orderType;
    /**购票数量*/
    @Column(defaultValue = "1")
    private  Integer orderNumber;
    /**总价*/
    @Column(nullable = false)
    private Integer sumPrice;

    /**用户ID*/
    @Column(nullable = false)
    private String tel;
    /**订单状态*/
    @Column(defaultValue = "可使用")
    private String orderStatue;
    /**是否可用*/
    @Column(defaultValue = "1")
    private int usable;
    /**景点名称*/
    private  String ScenicName;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOrderStatue() {
        return orderStatue;
    }

    public void setOrderStatue(String orderStatue) {
        this.orderStatue = orderStatue;
    }

    public int getUsable() {
        return usable;
    }

    public void setUsable(int usable) {
        this.usable = usable;
    }

    public String getScenicName() {
        return ScenicName;
    }

    public void setScenicName(String scenicName) {
        ScenicName = scenicName;
    }
}
