package com.cts.AuditManagement.Authorization.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cts.AuditManagement.Authorization.Service.JwtRequestFilter;
import com.cts.AuditManagement.Authorization.Service.UserDetailImp;


/*This class is used for the security configuration. It extends the class
WebSecurityConfigurerAdapter It will take care of the authentication and
authorization based on the user credentials.*/


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailImp user;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("START");
		auth.userDetailsService(user);
		logger.info("END");
		
	}

	/**
	 * This method is used for the configuration of authorization part.And all the
	 * requests which starts with '/authenticate' and all other requests will be
	 * authenticated and permitted.
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("START");
		SessionManagementConfigurer<HttpSecurity> sessionCreationPolicy = http.csrf().disable().authorizeRequests()
				.antMatchers("/authenticate").permitAll().anyRequest().authenticated().and().exceptionHandling().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		logger.debug("SESSION CREATE POLICY {}:", sessionCreationPolicy);
		HttpSecurity addFilterBefore = http.addFilterBefore(jwtRequestFilter,
				UsernamePasswordAuthenticationFilter.class);
		
		logger.debug("ADD FILTER BEFORE{}:", addFilterBefore);
		logger.info("END");
	}

	  @Override
			@Bean
			public AuthenticationManager authenticationManagerBean() throws Exception {
			    return super.authenticationManagerBean();
			}
	  
	  /**
		 * Password encoder is an interface which is used through the authorization
		 * process. The encode function shall be used to encode the password.
	 */
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
