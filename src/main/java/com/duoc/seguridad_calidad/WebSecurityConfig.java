package com.duoc.seguridad_calidad;

import com.duoc.seguridad_calidad.provider.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.context.annotation.Description;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig {


    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/", "/home", "/buscar","/login","/images/**.jpg").permitAll()
            .requestMatchers("/recetas/**").authenticated() 
            .requestMatchers("/**.css").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin((form) -> form
            .loginPage("/login")
            .defaultSuccessUrl("/home", true) // Redirect to /home after successful login
            .permitAll()
        )
        .logout((logout) -> logout.permitAll());

        return http.build();
    }

    
}