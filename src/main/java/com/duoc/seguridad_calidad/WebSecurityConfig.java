package com.duoc.seguridad_calidad;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
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
@EnableWebSecurity 
public class WebSecurityConfig {

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
            .permitAll()
        )
        .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    @Description("In memory Userdetails service registered since DB doesn't have user table ")
    public UserDetailsService users(){
        // The builder will ensure the passwords are encoded before saving in memory  
        UserDetails user = User.builder() 
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();
        UserDetails user2 = User.builder() 
            .username("user2")
            .password(passwordEncoder().encode("password2"))
            .roles("USER")
            .build();
        UserDetails admin = User.builder()
            .username("admin") 
            .password(passwordEncoder().encode("password")) 
            .roles("USER", "ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user,user2, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
