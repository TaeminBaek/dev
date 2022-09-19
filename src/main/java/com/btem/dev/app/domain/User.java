package com.btem.dev.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "USER_ID")
    private String id;

    private String pwd;

    @Column(name = "USER_NAME")
    private String name;

    private String phoneNumb;

    private String emailAddr;

    public User(String id, String name, String pwd, String phoneNumb, String emailAddr) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.phoneNumb = phoneNumb;
        this.emailAddr = emailAddr;
    }

    public void setName(String name) { this.name = name; }
    public void setPhoneNumb(String phoneNumb) { this.phoneNumb = phoneNumb; }
    public void setEmailAddr(String emailAddr) { this.emailAddr = emailAddr; }
}