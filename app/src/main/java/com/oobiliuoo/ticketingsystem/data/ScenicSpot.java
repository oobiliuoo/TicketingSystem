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


    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String level;
    private String openTime;
    @Column(nullable = false)
    private int picId;

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
}
