package com.taskmanager.task_manager.config;

import com.taskmanager.task_manager.users.service.impl.UserServiceDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Configuration
public class SecurityConfig {
    ///  User server implementation
    private final UserServiceDetails userServiceDetails;

    ///  Get the object
    SecurityConfig(UserServiceDetails userServiceDetails){
        this.userServiceDetails = userServiceDetails;
    }
    @Value("${app.version}")
    private String version;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        // Permit all the static resources
        httpSecurity.authorizeHttpRequests(
                configures -> configures.requestMatchers("/css/**" , "/js/**"  , "/taskmanager/" + version+"/auth/**", "/assests/**")
                        .permitAll().anyRequest().authenticated() // Authenticating all the other resource
        ).formLogin( // Login form
                form-> form
                        .loginPage("/taskmanager/"+version+"/auth/login") // Login form which it must direct
                        .loginProcessingUrl("/taskmanager/"+version+"/auth/loginprocess")
                        .defaultSuccessUrl("/taskmanager/" + version + "/dashboard/", true)// redirecting is success fulla
                        .permitAll()
        );

        return httpSecurity.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userServiceDetails);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;


    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
