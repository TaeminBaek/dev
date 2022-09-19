package com.btem.dev.security.authentication;

import com.btem.dev.app.domain.User;
import lombok.Getter;

@Getter
public class UserInfo {
    private String id;
    private String pwd;
    private String name;
    private String phoneNumb;
    private String emailAddr;

    public UserInfo(User user) {
        this.id = user.getId();
        this.pwd = user.getPwd();
        this.name = user.getName();
        this.phoneNumb = user.getPhoneNumb();
        this.emailAddr = user.getEmailAddr();
    }
}
