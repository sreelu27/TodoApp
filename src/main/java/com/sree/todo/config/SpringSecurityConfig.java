package com.sree.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        /*
      basic authentication to pass username & password in header of the request
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        authorize.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
        */

       /* httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN","USER");
                    authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN","USER");
                    //to allow it to public access
                    //authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();
                    authorize.anyRequest().authenticated();
                }
                ).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();

        */

        //Method level security
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.anyRequest().authenticated();
                }
                ).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    //create multiple users in memory authentication
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1= User.builder()
                .username("priya")
                .password(passwordEncoder().encode("priya"))
                .roles("USER")
                .build();

        UserDetails admin= User.builder()
                .username("pank")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return  new InMemoryUserDetailsManager(user1, admin);
    }

    //This method uses Bcrypt algorithm to hash the password
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
