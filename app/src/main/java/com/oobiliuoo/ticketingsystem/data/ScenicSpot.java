package com.oobiliuoo.ticketingsystem.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * @author biliu
 *
 * 景点信息
 *
 */
public class ScenicSpot extends LitePalSupport {



    /**景点名称*/
    @Column(nullable = false)
    private String name;
    /**景点类型*/
    @Column(nullable = false)
    private String level;
    /**景点开放时间*/
    private String openTime;
    /**景点图片*/
    @Column(nullable = false)
    private int picId;
    /**景点票价*/
    @Column(defaultValue = "0")
    private String price;
    /**景点简介*/
    @Column(defaultValue = "无")
    private String intro;

    public ScenicSpot() {
    }

    public ScenicSpot(String name, String level, String openTime, int picId) {
        this.name = name;
        this.level = level;
        this.openTime = openTime;
        this.picId = picId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
