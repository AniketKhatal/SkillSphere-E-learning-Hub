package com.backend.elearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	                .anyRequest().permitAll()  // Allow all requests without authentication
	                .and()
	                .csrf().disable();  // Disable CSRF if not needed
	    }

	    @Bean
	    BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
	
//	@Bean
//	public FilterRegistrationBean<CorsFilter> corsFilter() {
//		
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowCredentials(true);
//		corsConfiguration.addAllowedOriginPattern("*");
//		corsConfiguration.addAllowedHeader("Authorization");
//		corsConfiguration.addAllowedHeader("Content-Type");
//		corsConfiguration.addAllowedHeader("Accept");
//		corsConfiguration.addAllowedMethod("POST");
//		corsConfiguration.addAllowedMethod("GET");
//		corsConfiguration.addAllowedMethod("PUT");
//		corsConfiguration.addAllowedMethod("DELETE");
//		corsConfiguration.addAllowedMethod("OPTIONS");
//		corsConfiguration.setMaxAge(3600L);
//		
//		source.registerCorsConfiguration("/**", corsConfiguration);
//		
//		FilterRegistrationBean bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source)); 
//		
//		return bean;
//		
//	}
	
	
