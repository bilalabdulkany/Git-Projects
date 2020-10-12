package com.simpledev.oauthresource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		 http.cors()
         .and()
           .authorizeRequests()
             .antMatchers(HttpMethod.GET, "/user/info", "/api/foos/**")
               .hasAuthority("SCOPE_read")
             .antMatchers(HttpMethod.POST, "/api/foos")
               .hasAuthority("SCOPE_write")
             .anyRequest()
               .authenticated()
         .and()
           .oauth2ResourceServer()
             .jwt();
	}

	
}
