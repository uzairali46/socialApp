package com.meecaps.socialApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

 // Password encoder bean
    @Bean

     public PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Security filter chain

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("user/create").permitAll()
                                        .requestMatchers("/user/get/All").hasRole("ADMIN")
                                        .requestMatchers("/user/*").hasRole("USER")
                                        .anyRequest().authenticated()

                )
                .userDetailsService(userDetailsService).httpBasic(Customizer.withDefaults());
                return http.build();
    }
}
