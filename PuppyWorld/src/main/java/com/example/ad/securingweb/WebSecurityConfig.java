package com.example.ad.securingweb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.headers().frameOptions().disable();
        http.authorizeRequests()
        		.antMatchers("/css/**", "/js/**", "/webjars/**","/index", "/register", "/login", "/forgetPassword", "/passwordRegenerate", "/saveNewUser", "/images/**", "glyphicon glyphicon-home").permitAll()
        		.antMatchers("/registerStaff")
        			.access("hasRole(T(com.example.ad.domain.Role).ROLE_ADMIN.toString())")
        		.antMatchers("/productCreate")
        			.access("hasRole(T(com.example.ad.domain.Role).ROLE_ADMIN.toString())")
        		.antMatchers("/serviceCreate")
        			.access("hasRole(T(com.example.ad.domain.Role).ROLE_ADMIN.toString())")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            	.loginPage("/login")
	            .successHandler(new AuthenticationSuccessHandler() {
	                @Override
	                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	                        Authentication authentication) throws IOException, ServletException {
	                    redirectStrategy.sendRedirect(request, response, "/");
	                }
	            })
	            .permitAll()
            .and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) 
            	.logoutSuccessUrl("/login")
            	.permitAll();
    }
    
}
