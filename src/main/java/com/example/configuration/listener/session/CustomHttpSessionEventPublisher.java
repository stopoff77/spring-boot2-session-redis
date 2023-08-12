package com.example.configuration.listener.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.security.web.session.HttpSessionEventPublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomHttpSessionEventPublisher extends HttpSessionEventPublisher {

    public CustomHttpSessionEventPublisher() {
        // default constructor
    }


    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        int maxInactiveInterval = session.getMaxInactiveInterval(); // session timeout(interval, in seconds)

        log.debug("SESSION CREATED {}[{}], [{}]", session.getId(), session.getCreationTime(), maxInactiveInterval);
        super.sessionCreated(event);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        int maxInactiveInterval = session.getMaxInactiveInterval();

        long currentTime = System.currentTimeMillis();
        long createTime  = session.getCreationTime();
        long holdingTime = (currentTime - createTime);

        log.debug("SESSION DESTROYED {}[{} - {} = {}], [{}]", session.getId(), currentTime, createTime, holdingTime, maxInactiveInterval);
        session.invalidate(); // session invalidate 직접 호출
        super.sessionDestroyed(event);
    }
}
