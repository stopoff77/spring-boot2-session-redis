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
