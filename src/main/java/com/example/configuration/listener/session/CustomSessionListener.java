package com.example.configuration.listener.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomSessionListener implements HttpSessionListener {@Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        log.debug("SESSION CREATED [{}]", session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        log.debug("SESSION DESTROYED [{}]", session.getId());
    }

}
