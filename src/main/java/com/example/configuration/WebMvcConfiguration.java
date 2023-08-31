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
package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    public WebMvcConfiguration() {
        // default constructor
    }


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // default content type을 json으로 설정
        configurer.defaultContentType(MediaType.APPLICATION_JSON)
                  .ignoreAcceptHeader(true)

                  // parameter name - mediaType - 에 따른 response type 설정
                  .favorParameter    (true)
                  .parameterName     ("mediaType")
                  .mediaType("xml" , MediaType.APPLICATION_XML)
                  .mediaType("json", MediaType.APPLICATION_JSON)
                  ;
    }


    @Bean
    MappingJackson2JsonView jsonView() {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();

        // 브라우저에서 직접 호출시 에러 유무에 따라 이부분이 불필요 할 수도 있음
        // 브라우저에서 직접 호출시 json data가 안나오고 오류 발생시 아래 부분 필요
        // 브라우저에서 직접 호출시 json data가 잘 나오면 아래 부분 불필요
        jsonView.setContentType(MediaType.TEXT_HTML_VALUE);

        return jsonView;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler( "/assets/**"   ).addResourceLocations( "classpath:/static/assets/"  , "/static/assets/" );
        registry.addResourceHandler( "/images/**"   ).addResourceLocations( "classpath:/static/images/"  , "/static/images/" );
        registry.addResourceHandler( "/js/**"       ).addResourceLocations( "classpath:/static/js/"      , "/static/js/"     );
        registry.addResourceHandler( "/plugins/**"  ).addResourceLocations( "classpath:/static/plugins/" , "/static/plugins/");
        registry.addResourceHandler( "/templates/**").addResourceLocations( "classpath:/templates/"      , "/templates/"     );
        registry.addResourceHandler( "/css/**"      ).addResourceLocations( "classpath:/static/css/"     , "/static/css/"    );
        registry.addResourceHandler( "/editor/**"   ).addResourceLocations( "classpath:/static/editor/"  , "/static/editor/" );
        registry.addResourceHandler( "/favicon.ico" ).addResourceLocations( "/favicon.ico");
    }
}

