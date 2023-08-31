/**
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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */
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
