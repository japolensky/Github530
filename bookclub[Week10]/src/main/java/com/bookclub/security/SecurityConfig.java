package com.bookclub.security;
// original Krasso project uses spring-boot-starter-security 2.5.2 - the WebSecurityConfigurerAdapter is deprecated
// Craig Walls is the security guru on the Spring team (Yet he hasn't updated his chapter either), it is possibly time to adjust this portion of the
// Project to OAUTH2, possibly using the GitHub login as the OAUTH provider
// https://github.com/spring-projects/spring-security/issues/10822  <-- Eleftheria's discussion is enlightening on the difficulty of conversion.


// a similar strategy as original is found here, I've adapted it to the Krasso Project:

// https://www.youtube.com/watch?v=a-4LK0FiqNQ  <-gitHub authorization
// https://www.youtube.com/watch?v=us0VjFiHogo  <-oauth2 SecurityConfig strategy gitHub/google login

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// https://github.com/danvega/security-demo/blob/master/src/main/java/dev/danvega/securitydemo/config/SecurityConfig.java
// https://www.youtube.com/watch?v=s4X4SJv2RrU <-Dan Vega shows the replacement strategy for configurer adapter
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("testuser01")
                .password(passwordEncoder().encode("password01"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password01"))
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        return http
                .csrf(csrf ->csrf.disable())
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers("/login").permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();
    }
    // I'm not sure about this here, I am trying to find a good example that is straightforward
    // for having both the login by password / oauth2 strategy, it works in this example
    // finding the correct way to show the username for each of the login modes is elusive
    // deciding role for external partner authentication should be user until
    // authorized for admin or other role?


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}