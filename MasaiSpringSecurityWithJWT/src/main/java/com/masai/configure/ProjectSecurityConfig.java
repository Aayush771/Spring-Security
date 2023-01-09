package com.masai.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.masai.filter.JwtTokenGeneratorFilter;
import com.masai.filter.JwtTokenValidatorFilter;

@Configuration
public class ProjectSecurityConfig {

	
	@Bean
	public SecurityFilterChain masaiSecurityFilter(HttpSecurity http) throws Exception {
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeHttpRequests( (auth)->auth
				.requestMatchers("/masai/welcomeP","/masai/account").authenticated()
				.requestMatchers("/masai/employee/admin").hasRole("admin")
				.requestMatchers("/masai/welcome","/masai/employee/register").permitAll()
		)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.csrf().disable()
				.formLogin().and().httpBasic();
		
		
		

		return http.build();
	}
	
//	@Bean
//	public InMemoryUserDetailsManager userDetails() {
//		
//		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//	  UserDetails admin = User.withUsername("admin").password("12345").authorities("admin").build();
//	    UserDetails user = User.withUsername("user").password("12345").authorities("read").build();
//	    userDetailsService.createUser(admin);
//	    userDetailsService.createUser(user);
//	    return userDetailsService;
//	}
	
	 @Bean
	 public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	 }
}
