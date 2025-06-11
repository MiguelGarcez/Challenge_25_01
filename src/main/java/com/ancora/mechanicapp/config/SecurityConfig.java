package com.ancora.mechanicapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(a -> a.requestMatchers("/css/**").permitAll().anyRequest().authenticated())
            .formLogin(Customizer.withDefaults())
            .logout(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService users(){
        var mgr = new org.springframework.security.provisioning.InMemoryUserDetailsManager();
        mgr.createUser(org.springframework.security.core.userdetails.User
            .withUsername("admin").password("{noop}password").roles("ADMIN").build());
        return mgr;
    }
}
