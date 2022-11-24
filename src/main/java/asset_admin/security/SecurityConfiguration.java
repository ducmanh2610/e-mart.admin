package asset_admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import asset_admin.daoImpl.UserDAOImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home", "resources/**").permitAll().antMatchers("/admin/**")
				.access("hasRole('ADMIN')").antMatchers("/user/**").access("hasRole('USER')").antMatchers("/manager/**")
				.access("hasRole('MANAGER')").antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll().and().httpBasic();
		return http.build();
	}

	@Bean
	public UserDetailsService customUserDetailsService() throws Exception {
		return new UserDAOImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	 public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.httpFirewall(new LoggingHttpFirewall());
    }
}