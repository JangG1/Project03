package com.FT.app.security.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.FT.app.Login.API.ConfigLoader;


// WebSecurityConfigurerAdapter 사용 중지(2022.02.21)
//Security Config 설정 공식 문서 : https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
//SecurityFilterChain 빈을 사용해 보안 설정을 더욱 구체적으로 적용하기 위함이고 추세이기 때문
@Configuration
public class SecurityConfig {

    ConfigLoader configLoader = new ConfigLoader();
        
    String exIP = configLoader.getExIP();
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.cors().configurationSource(configurationSource());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        
        //해당 경로에 대해 Spring Security가 인증을 요구하지 않고 모든 요청을 허용
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/api/**").permitAll();
        http.authorizeRequests().antMatchers("/api/auth/kakao/callback").permitAll();
        http.authorizeRequests().antMatchers("/api/auth/kakao/callback2").permitAll();
        http.authorizeRequests().antMatchers("/res/**").permitAll();
        http.authorizeRequests().antMatchers("/res/resPost").permitAll();
        http.authorizeRequests().antMatchers("/api/res/resPost").permitAll();
        
        return http.build();
    }

    public CorsConfigurationSource configurationSource() {    	
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("https://www.fastrip.store", "https://www.fastrip.store:8200", exIP, exIP+":9200", exIP+":8200"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true); // 인증 관련 요청 허용
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}