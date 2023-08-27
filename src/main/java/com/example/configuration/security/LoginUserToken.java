/*
 * Copyright 2020 장동선.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
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
