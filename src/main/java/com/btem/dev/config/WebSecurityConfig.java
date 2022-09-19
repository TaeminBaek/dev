package com.btem.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	public static final String ADMIN_LOGIN_PAGE = "/adminLoginPage";
	public static final String PARTNER_LOGIN_PAGE = "/loginPage";
	public static final String LOGIN_PROCESS_URL = "/user/doLogin";
	public static final String LOGIN_SUCCESS_URL = "/fullScreen";
	public static final String LOGOUT_SUCCESS_URL = "/user/logoutSuccess";
	public static final String ACCESS_DENIED_ERROR_URL = "/error";
	public static final String REQUEST_ATTRIBUTE_REQUEST_URI_BEFORE_SECURITY = "REQUEST_URI_BEFORE_SECURITY";
	public static final String PARTNER_LOGIN_SUCCESS_URL = "/partner/home";
	public static final String EMP_LOGIN_SUCCESS_URL = "/home";

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.headers()
				.frameOptions().sameOrigin()
				.and()
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/", "/login").permitAll()
				.anyRequest().authenticated()
				.and()
				.cors().and()
			.logout()
				.logoutUrl("/logout").permitAll()
				.and()
			.sessionManagement()
				.and();
		return http.build();
	}
}
