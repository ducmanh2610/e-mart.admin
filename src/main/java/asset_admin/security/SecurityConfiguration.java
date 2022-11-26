package asset_admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import asset_admin.daoImpl.UserDAOImpl;

//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration{

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "home").permitAll()
				.antMatchers("/admin/**")
				.access("hasRole('ADMIN')").antMatchers("/user/**").access("hasRole('USER')")
				.antMatchers("/manager/**")
				.access("hasRole('MANAGER')").antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll().and().httpBasic();
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDAOImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}