package com.example.common.filter.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.configuration.security.LoginUserToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserTokenFilter extends OncePerRequestFilter {

    public UserTokenFilter() {
        log.debug("UserTokenFilter default constructor");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        log.debug("requestUri {}", requestUri);

        // RunAsUserToken은 권한체크(SecurityAccessAuthority)만 실행
        // UsernamePasswordAuthenticationToken은 LoginAuthenticationProvider 및 권한체크 실행
        LoginUserToken authToken = new LoginUserToken("", requestUri, "", null, null);
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

}
