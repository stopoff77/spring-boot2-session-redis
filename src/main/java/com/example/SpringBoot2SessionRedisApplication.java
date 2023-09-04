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
package com.example;

import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.example.configuration.ComponentScanConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAutoConfiguration(exclude = {
        DataSourceTransactionManagerAutoConfiguration.class
      , JndiDataSourceAutoConfiguration.class
      , SecurityAutoConfiguration.class
      , JacksonAutoConfiguration.class
//      , HttpClientMetricsAutoConfiguration.class // spring-boot-actuator-autoconfigure
//    , org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration.class
})
@SpringBootApplication( scanBasePackageClasses = { ComponentScanConfiguration.class } )
public class SpringBoot2SessionRedisApplication {

    /**
     * default Locale & default TimeZone 설정
     */
    public SpringBoot2SessionRedisApplication(
            @Value("${project.locale}"   ) Locale defaultLocale,
            @Value("${project.time-zone}") TimeZone defaultTimeZone) {

        log.debug("yaml locale   {}", defaultLocale);
        log.debug("yaml locale   {}", Locale.KOREA);

        log.debug("yaml timeZone {}", defaultTimeZone.getID());
        log.debug("yaml timeZone {}", defaultTimeZone.getDisplayName());
        log.debug("yaml timeZone {}", defaultTimeZone.getID());
        log.debug("yaml timeZone {}", TimeZone.getTimeZone("UTC").getID());
        log.debug("yaml timeZone {}", TimeZone.getDefault().getID());

        // default Locale, default TimeZone 설정
        Locale.setDefault(defaultLocale);
        TimeZone.setDefault(defaultTimeZone);

        //
        log.debug("yaml locale   {}", Locale.getDefault());
        log.debug("yaml timeZone {}", TimeZone.getDefault().getID());
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2SessionRedisApplication.class, args);
	}

}
