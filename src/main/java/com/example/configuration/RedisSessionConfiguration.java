package com.example.configuration;

import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import com.example.configuration.listener.session.CustomHttpSessionEventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

//@EnableRedisHttpSession(redisNamespace = "${spring.session.redis.namespace}")
@Configuration
public class RedisSessionConfiguration implements BeanClassLoaderAware {

    @Value("${server.servlet.session.cookie.name}")
    private String name;

    @Value("${server.servlet.session.cookie.domain}")
    private String domain;

    @Value("${server.servlet.session.cookie.path}")
    private String path;

    @Value("${server.servlet.session.cookie.http-only}")
    private boolean httpOnly;

    @Value("${server.servlet.session.cookie.secure}")
    private boolean secure;

    @Value("${server.servlet.session.cookie.same-site}")
    private String sameSite;

    //
    private ClassLoader loader;


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.loader = classLoader;
    }

    /**
     * Customized {@link ObjectMapper} to add mix-in for class that doesn't have default
     * constructors
     * @return the {@link ObjectMapper} to use
     */
    private ObjectMapper objectMapper() {
        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL) // Object => Json (null field ignore)
                .failOnUnknownProperties(false)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .featuresToDisable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS)
                .timeZone(TimeZone.getDefault())
                .locale(Locale.getDefault())
                .simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                .build();

        mapper.registerModules(SecurityJackson2Modules.getModules(this.loader));

        return mapper;
    }


    @Bean
    RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer(objectMapper());
    }

    @Bean
    CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setUseBase64Encoding(false);
        serializer.setCookieName(name);
        serializer.setDomainName(domain);
        serializer.setCookiePath(path);
        serializer.setUseHttpOnlyCookie(httpOnly);
        serializer.setUseSecureCookie(secure);
        serializer.setSameSite(sameSite);

//        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return serializer;
    }


    // session listener
    @Bean
    CustomHttpSessionEventPublisher httpSessionListener() {
//        return new CustomSessionListener();
        return new CustomHttpSessionEventPublisher();
    }// Register HttpSessionEventPublisher

}
