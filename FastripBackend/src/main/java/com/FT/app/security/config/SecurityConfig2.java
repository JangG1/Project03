//package com.FT.app.security.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
///*import shop.mtcoding.bank.config.jwt.JwtAuthenticationFilter;
//import shop.mtcoding.bank.config.jwt.JwtAuthorizationFilter;
//import shop.mtcoding.bank.domain.user.UserEnum;*/
//
///*
// * 시큐리티 컨피그 설정 공식 문서
// * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
// */
//@Configuration
//public class SecurityConfig2 {
//
//	//private final Logger log = LoggerFactory.getLogger(getClass());
//
//	 @Bean
//	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//	            throws Exception {
//	        return authenticationConfiguration.getAuthenticationManager();
//	    }
//	 
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    	http.httpBasic().disable();
//		http.cors().configurationSource(configurationSource());
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.csrf().disable();
//		
//        http.authorizeRequests().antMatchers("/").permitAll();
//
//		http.authorizeRequests().antMatchers("/api/**").permitAll();
//
//		http.authorizeRequests().antMatchers("/api/auth/kakao/callback").permitAll();
//        return http.build();
//    }
//
//	// CORS 허용 적용
//	public CorsConfigurationSource configurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.addAllowedOrigin("*");
//		configuration.addAllowedOriginPattern("*");
//		configuration.addAllowedHeader("*");
//		configuration.addAllowedMethod("*");
//
//		configuration.addAllowedOriginPattern("*"); // 프론트 서버의 주소 등록
//		configuration.setAllowCredentials(true); // 클라이언트에서 쿠키 요청 허용
//		configuration.addExposedHeader("Authorization");
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
//}