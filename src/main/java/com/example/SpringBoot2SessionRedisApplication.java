package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.example.configuration.ComponentScanConfiguration;

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

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2SessionRedisApplication.class, args);
	}

}
