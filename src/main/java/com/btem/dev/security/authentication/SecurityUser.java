package com.btem.dev.security.authentication;

import com.btem.dev.app.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUser implements UserDetails {

    private String userName;
    private String password;
    private String name;
    private String phoneNumb;
    private String emailAddr;

    public SecurityUser(User user) {
        this.userName = user.getId();
        this.password = user.getPwd();
        this.name = user.getName();
        this.phoneNumb = user.getPhoneNumb();
        this.emailAddr = user.getEmailAddr();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
