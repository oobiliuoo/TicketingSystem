package com.oobiliuoo.ticketingsystem.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * @author biliu
 * 用户信息
 */
public class UserInfo extends LitePalSupport {

    @Column(nullable = false)
    private String tel;
    @Column(defaultValue = "unKnow")
    private String nickName;
    @Column(nullable = false)
    private String pwd;

    public UserInfo() {
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
