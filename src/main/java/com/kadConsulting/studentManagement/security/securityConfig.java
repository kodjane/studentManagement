package com.kadConsulting.studentManagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
 * Created By
 * @author Aime D. Kodjane
 */
@Configuration
public class securityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails kodjane = User.builder()
                .username("kodjane")
                .password("{noop}test1234")
                .roles("STUDENT")
                .build();

        UserDetails dusenge = User.builder()
                .username("dusenge")
                .password("{noop}test1234")
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(kodjane, dusenge);
    }
}
