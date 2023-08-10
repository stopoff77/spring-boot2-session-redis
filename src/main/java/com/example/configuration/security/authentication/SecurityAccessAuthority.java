package com.example.configuration.security.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SecurityAccessAuthority {

    public boolean isAccessible(Authentication authentication, HttpServletRequest request) {
        //
        String requestURI = request.getRequestURI();
        log.debug("SecurityAccessAuthority {}", requestURI);


        if(authentication == null) {
            return false;
        }

        Object principal  = authentication.getPrincipal();

        log.debug("SecurityAccessAuthority {}", authentication);
        log.debug("SecurityAccessAuthority {}", authentication.isAuthenticated());
        log.debug("SecurityAccessAuthority {}", principal);


        return true;
    }
}
