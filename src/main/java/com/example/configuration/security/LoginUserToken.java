package com.example.configuration.security;

import java.util.Collection;

import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;

@JsonTypeInfo(defaultImpl = SecurityContextImpl.class, use = JsonTypeInfo.Id.NONE)
@Getter
public class LoginUserToken extends RunAsUserToken {

    private static final long serialVersionUID = 1L;


    public LoginUserToken() {
        this("", null, null, null, null);
    }

    public LoginUserToken(String key, Object principal, Object credentials
            , Collection<? extends GrantedAuthority> authorities
            , Class<? extends Authentication> originalAuthentication) {
        super(key, principal, credentials, authorities, originalAuthentication);
    }
}
