package com.ggm.cafemanagement.config;

import com.ggm.cafemanagement.domain.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ROLE_PREFIX = "ROLE_";

    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Autowired
    public WebSecurityConfig(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    @PostConstruct
    public void init() {
        requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .regexMatchers("/table/\\d\\??", "/order/\\d/\\d", "/product-in-order/\\d").hasAnyAuthority(ROLE_PREFIX + RoleEnum.MANAGER.name(), ROLE_PREFIX + RoleEnum.WAITER.name())
                .antMatchers("/order/**", "/product-in-order/**").hasAnyAuthority(ROLE_PREFIX + RoleEnum.WAITER.name())
                .antMatchers("/user/**", "/table/**", "/product/**").hasAnyAuthority(ROLE_PREFIX + RoleEnum.MANAGER.name())
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("userName")
                .passwordParameter("password")
                .defaultSuccessUrl("/home", true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login").permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/webapp/**");
    }

}
