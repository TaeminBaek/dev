package com.btem.dev.security.authentication;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private UserInfo userInfo;

    public CustomAuthenticationToken(Object principal, Object credentials, UserInfo userInfo) {
        super(principal, credentials);
        this.userInfo = userInfo;
    }
}
