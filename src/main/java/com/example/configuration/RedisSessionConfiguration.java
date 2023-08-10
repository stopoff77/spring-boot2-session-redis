package com.example.configuration;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import com.example.configuration.listener.CustomSessionListener;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModules(SecurityJackson2Modules.getModules(this.loader));
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        return mapper;
    }


    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer(objectMapper());
    }

    @Bean
    public CustomSessionListener customSessionListener() {
       return new CustomSessionListener();
    }

    @Bean
    public CookieSerializer cookieSerializer() {
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
}
