package org.abijay.talenthire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration - define Spring beans in the class
// Inorder to enable spring security support in Spring MVC application use @EnableWebSecurity
@Configuration
@EnableWebSecurity
public class WebSpringSecurity {
    // define security filter chain bean
    // authorizeHttpRequest method takes an implementation lambda expression
    // authenticate anyRequest
    // form login authentication through formLogin method that takes an implementation of lambda expression
    // once authenticated successfully, user is navigated to /talent/myclients by default
    // loginProcessingUrl is the Spring default url
    // filterchain method returns Security Filter Chain instance - through build method
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // csrf() is used mainly for banking transaction applications
        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/talent/myclients")
                        .loginProcessingUrl("/login")
                        .permitAll()
                );
        return httpSecurity.build();
    }
}
