package com.usrmanagement.config;

import com.usrmanagement.Security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationProvider userAuthenticationProvider;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login","/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return  http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationManager authenticationManagerBean(HttpSecurity http)throws Exception{

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(userAuthenticationProvider);

        return authenticationManagerBuilder.build();
    }

}
