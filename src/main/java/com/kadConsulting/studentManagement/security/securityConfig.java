package com.kadConsulting.studentManagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

        UserDetails joshua = User.builder()
                .username("joshua")
                .password("{noop}test1234")
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(kodjane, dusenge, joshua);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests()
//                config -> config
                                .requestMatchers(HttpMethod.GET, "api/v1/students").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.GET, "api/v1/students/**").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.POST, "api/v1/students").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT, "api/v1/students/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE, "api/v1/students/**").hasRole("MANAGER")
                .requestMatchers(
                        "swagger-ui/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**").permitAll();
        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }
}
