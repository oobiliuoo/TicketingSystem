package com.oobiliuoo.ticketingsystem.data;

import org.litepal.annotation.Column;

import java.io.Serializable;

/**
 * @author biliu
 * 通知文本类
 */
public class NotifyText  implements Serializable {

    /**通知标题*/
    @Column(nullable = false)
    private String title;
    /**通知时间*/
    @Column(nullable = false)
    private String time;
    /**通知内容*/
    @Column(nullable = false)
    private String text;


    public NotifyText() {
    }


    public NotifyText(String title, String time, String text) {
        this.title = title;
        this.time = time;
        this.text = text;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }


}
