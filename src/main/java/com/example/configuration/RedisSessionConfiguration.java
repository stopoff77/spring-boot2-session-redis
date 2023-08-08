package com.example.configuration;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.example.configuration.listener.CustomSessionListener;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableRedisHttpSession(redisNamespace = "${spring.session.redis.namespace}")
@Configuration
public class RedisSessionConfiguration implements BeanClassLoaderAware {

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
}
