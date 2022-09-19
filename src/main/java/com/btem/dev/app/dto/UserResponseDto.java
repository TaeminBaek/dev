package com.btem.dev.app.dto;

import com.btem.dev.app.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String id;
    private String name;
    private String phoneNumb;
    private String emailAddr;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNumb = user.getPhoneNumb();
        this.emailAddr = user.getEmailAddr();
    }
}
