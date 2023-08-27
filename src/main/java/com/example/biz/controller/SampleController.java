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
package com.example.biz.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SampleController {

    @RequestMapping(value={"", "/", "/home"})
    public ResponseEntity<Object> index(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> body = new TreeMap<>();

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                String name = cookie.getName();
                String value= cookie.getValue();

                log.debug("cookie {}[{}]", name, value);
            }
        }

        HttpSession session = request.getSession();

        String sessionId = session.getId();
        String requestedSessionId = request.getRequestedSessionId();

        body.put("sessionId", sessionId);
        body.put("requestedSessionId", requestedSessionId);

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value={"/logout"})
    public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> body = new TreeMap<>();

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                String name = cookie.getName();
                String value= cookie.getValue();

                log.debug("cookie {}[{}]", name, value);
            }
        }

        HttpSession session = request.getSession();

        String sessionId = session.getId();
        String requestedSessionId = request.getRequestedSessionId();

        body.put("sessionId", sessionId);
        body.put("requestedSessionId", requestedSessionId);

        session.invalidate();

        return ResponseEntity.ok(body);
    }
}
