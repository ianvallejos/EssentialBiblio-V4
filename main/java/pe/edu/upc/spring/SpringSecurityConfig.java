package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.spring.auth.handler.LoginSuccessHandler;
import pe.edu.upc.spring.serviceimpl.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private LoginSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		try {
			http.authorizeRequests()

					.antMatchers("/libro/**").access("hasRole('ROLE_ADMIN') ")
					.antMatchers("/mantenimiento/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/prestamo/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
					.antMatchers("/ejemplar/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
					.antMatchers("/usuario/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/autor/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
					.antMatchers("/libro/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").and()
					.formLogin().successHandler(successHandler).loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/libro/listar")
					.permitAll().and().logout().logoutSuccessUrl("/login").permitAll().and().exceptionHandling().accessDeniedPage("/error_403");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}
}
