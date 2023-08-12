package com.example.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.example.common.filter.web.security.UserTokenFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    protected final CharacterEncodingFilter     characterEncodingFilter;

    @Value("${server.servlet.session.cookie.name}")
    private String sessionCookieName;


    /** constructor */
    public SecurityConfiguration(
          CharacterEncodingFilter characterEncodingFilter
    ) {
        this.characterEncodingFilter = characterEncodingFilter;

        log.debug("characterEncodingFilter {}", characterEncodingFilter);
        log.debug("characterEncodingFilter {}", characterEncodingFilter.isForceRequestEncoding());
        log.debug("characterEncodingFilter {}", characterEncodingFilter.isForceResponseEncoding());
        log.debug("characterEncodingFilter {}", characterEncodingFilter.getEncoding());
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        // security 설정 제외(security filter에 안걸림)
        List<String> ignoreList = Arrays.asList(
                  "/favicon.ico"
                , "/**/*.ico"
                , "/h2-console/**" // in-memory db console 화면
        );

        return (web) -> web.ignoring().antMatchers(ignoreList.toArray(new String[0]));
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.debug("sessionCookieName {}", sessionCookieName);

        // csrf & header
        http.csrf().disable(); // 테스트

        // CORS
        http.cors();

        // HSTS - Http Strict Transport Security
        // https 요청시에만 응답헤더에 HSTS를 추가 함
        http.headers()
            .httpStrictTransportSecurity()
            .includeSubDomains(true)
            .maxAgeInSeconds(31536000)

            // XSS Safe with Spring Security
            .and()
            .xssProtection()
            .and()
            .contentSecurityPolicy("script-src 'self'");
            ;

        http.authorizeRequests()
            // 모든 사용자 접속 가능
            .antMatchers(
                  "/"
                , "/error/**"
            ).permitAll()
            ;

        http.authorizeRequests()
        // 그외 권한 체크하여 접속 여부 구분 (expression 사용)
        .anyRequest()
        .access("@securityAccessAuthority.isAccessible(authentication, request)")
        ;

        http.formLogin().disable();
        http.logout().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        //SpringSecurity 사용시. CsrfFilter 앞에 CharacterEncodingFilter를 놓아야 한다.
        http.addFilterBefore(characterEncodingFilter, CsrfFilter.class);

        // 토큰 인증 필터
        http.addFilterAfter(userTokenFilter(), LogoutFilter.class);

        return http.build();
    }


    @Bean
    public UserTokenFilter userTokenFilter() {
        return new UserTokenFilter();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
