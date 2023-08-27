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
package com.example.configuration;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ComponentScan              ( basePackages = { "com.example" } )
@ConfigurationPropertiesScan( basePackages = { "com.example" } ) // @ConfigurationProperties Scan 설정
@ServletComponentScan       ( basePackages = { "com.example" } ) // @WebFilter, @WebListener, @WebServlet annotation 사용하기 위한 annotation
@Configuration
public class ComponentScanConfiguration {

    public ComponentScanConfiguration() {
        log.debug("{} default Constructor", this.getClass().getSimpleName());
    }
}
