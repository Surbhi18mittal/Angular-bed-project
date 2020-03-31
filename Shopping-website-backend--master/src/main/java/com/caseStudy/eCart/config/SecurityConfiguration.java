package com.caseStudy.eCart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.util.Password;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    public void globalSecurityConfig(AuthenticationManagerBuilder auth)throws Exception {
        auth.
                jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,active from login_details where username=?")
                .authoritiesByUsernameQuery("select username,role from login_details where username=?");
    }
    @Override
   protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
            .authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
           .antMatchers("/api/addUsers").permitAll()
                .antMatchers("/api/notes").permitAll()
                .antMatchers("/api/notes/**").permitAll()
                .antMatchers("/api/products/category/**").permitAll()
                .antMatchers("/api/getByPrice/**").permitAll()
           .anyRequest().authenticated()
            .and().httpBasic();
        http.cors();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
   }
}
