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
package com.example.configuration.security.authority;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccessAuthority {

    public boolean isAccessible(Authentication authentication, HttpServletRequest request) {
        //
        String requestURI = request.getRequestURI();
        log.debug("AccessAuthority {}", requestURI);

        if (authentication == null) {
            return false;
        }

        Object principal = authentication.getPrincipal();

        log.debug("AccessAuthority {}", authentication);
        log.debug("AccessAuthority {}", authentication.isAuthenticated());
        log.debug("AccessAuthority {}", principal);

        return true;
    }
}
