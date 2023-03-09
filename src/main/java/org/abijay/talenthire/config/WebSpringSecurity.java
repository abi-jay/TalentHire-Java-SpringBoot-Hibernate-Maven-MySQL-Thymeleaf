package org.abijay.talenthire.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// @Configuration - define Spring beans in the class
// Inorder to enable spring security support in Spring MVC application use @EnableWebSecurity
@Configuration
@EnableWebSecurity
public class WebSpringSecurity {

    // inject CustomUserDetailsService Bean
    // to achieve loose coupling inject interface not the implementation class
    private UserDetailsService userDetailsService;

    public WebSpringSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // configure spring bean for password encoder that uses BCrypt algorithm
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // define security filter chain bean
    // authorizeHttpRequest method takes an implementation lambda expression
    // access to all url that starts with /resources, /register
    // form login authentication through formLogin method that takes an implementation of lambda expression
    // once authenticated successfully, user is navigated to /talent/myclients by default
    // loginProcessingUrl is the Spring default url
    // filterchain method returns Security Filter Chain instance - through build method
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // csrf() is used mainly for banking transaction applications
        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(new AntPathRequestMatcher("/resources/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/register/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/admin/**"))
                                .hasAnyRole("ADMIN","GUEST")
                                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/talent/**")).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/talent/mytalents")
                        .loginProcessingUrl("/login")
                        .permitAll()
                ).logout( logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                );
        return httpSecurity.build();
    }

    // Set UserDetailsService and PasswordEncoder to authentication manager
    // @Autowired to inject userDetailsService and passwordEncoder to authentication manager
    // AuthenticationManagerBuilder internally uses userDetailsService to load the user object from the database and authenticate with database
    // AuthenticationManagerBuilder internally uses passwordEncoder to encrypt password
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
