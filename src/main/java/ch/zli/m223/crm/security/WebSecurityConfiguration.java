package ch.zli.m223.crm.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ch.zli.m223.crm.role.Roles;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	UserDetailsService userDetailsService;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(bCryptPasswordEncoder)
		;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		configureWeb(http);
		//configureRest(http);
	}

	private void configureRest(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			.cors().disable() //for developement only
			.sessionManagement().disable()
			.httpBasic()
			.and().authorizeRequests().antMatchers("/api/v0/users").hasAnyAuthority(Roles.ADMIN, Roles.USER)
			.antMatchers("/api/v0/users/*/roles").hasAnyAuthority(Roles.ADMIN)
			.anyRequest().authenticated()
		;
	}

	private void configureWeb(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/web/**").hasAuthority(Roles.ADMIN)
			.antMatchers(HttpMethod.POST, "/web/**").hasAuthority(Roles.ADMIN)
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().permitAll()
			.and().logout().permitAll()
			.and().csrf().disable()
		;
	}
}
