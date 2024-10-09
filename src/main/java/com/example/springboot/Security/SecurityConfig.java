package com.example.springboot.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.springboot.Model.AppUserService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private final AppUserService appUserService;
    
    /**
     * This bean defines the custom UserDetailsService, which loads user-specific data
     * from your database. Here, it uses AppUserService, which implements the logic to fetch user details.
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return appUserService;
    }
    
    /**
     * This method configures the AuthenticationProvider to use the DaoAuthenticationProvider.
     * - DaoAuthenticationProvider handles authentication against a database using user details.
     * - It uses the custom UserDetailsService and PasswordEncoder to validate credentials.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    
     /**
     * This bean defines the PasswordEncoder used for encoding and verifying passwords.
     * - BCryptPasswordEncoder is a strong hashing algorithm designed to protect passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Configures the security filter chain, specifying which requests should be authenticated,
     * login and logout settings, and disabling CSRF for the sake of simplicity.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            // Configuring OAuth2 login
            .oauth2Login(oauth2login -> {
                oauth2login.loginPage("/login");
                oauth2login.successHandler((request, response, authentication) -> response.sendRedirect("/"));
            })
            // Configuring form-based login
            .formLogin(httpForm ->{
                httpForm.loginPage("/login").permitAll();
                httpForm.failureUrl("/login?error");
                httpForm.defaultSuccessUrl("/");
                
            })
    
            // Authorization rules for HTTP requests
            .authorizeHttpRequests(registry ->{
                registry.requestMatchers("/register", "/css/**","/js/**").permitAll();
                registry.anyRequest().authenticated();
            })
            // Build the security filter chain
            .build();
    }
    
}