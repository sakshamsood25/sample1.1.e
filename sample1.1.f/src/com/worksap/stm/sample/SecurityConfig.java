package com.worksap.stm.sample;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.worksap.stm.sample.utils.PasswordHash;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	RequestMatcher csrfRequestMatcher = new RequestMatcher() {

		private String allowedMethod = "GET";

		private AntPathRequestMatcher[] requestMatchers = {
				new AntPathRequestMatcher("/login"),
				new AntPathRequestMatcher("/logout"),
				new AntPathRequestMatcher("/office"),
				new AntPathRequestMatcher("/employeemanagement/**"),
				new AntPathRequestMatcher("/offices"),
				new AntPathRequestMatcher("/author"),
				new AntPathRequestMatcher("/publisher"),
				new AntPathRequestMatcher("/series"),
				new AntPathRequestMatcher("/roadshows/**"),
				new AntPathRequestMatcher("/sales/**"),
				new AntPathRequestMatcher("/allpersons/**"),
				new AntPathRequestMatcher("/quickstart**"),
				new AntPathRequestMatcher("/addtasks/**"),
				new AntPathRequestMatcher("/addnotifs/**"),
				new AntPathRequestMatcher("/report/**"),
				new AntPathRequestMatcher("/books/**") };

		@Override
		public boolean matches(HttpServletRequest request) {
			for (AntPathRequestMatcher rm : requestMatchers) {
				if (rm.matches(request)) {
					return false;
				}
			}
			if (request.getMethod().equals(allowedMethod)) {
				return false;
			}
			return true;
		}

	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().requireCsrfProtectionMatcher(csrfRequestMatcher).and()
				.authorizeRequests().antMatchers("/resources/**", "/login/**")
				.permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").failureUrl("/login")
				.usernameParameter("userId").permitAll().and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(
				new PasswordHash());
	}
}