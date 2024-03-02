package com.advise_clothes.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
//@EnableWebFluxSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * application.yml으로 옮기고 싶었으나 실패하고 다시 복구했다..
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic().disable()
                .csrf().disable();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        List<String> origins = Arrays.asList(
//                "http://localhost:3000",
//                "https://web-advise-clothes-front-am952nlt1gbj1t.sel5.cloudtype.app"
//        );
//
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(origins);
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/api/**", configuration);
//        return source;
//    }
//
//    @Bean
//    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http
//                .cors(ServerHttpSecurity.CorsSpec::disable)
//                .httpBasic().disable()
//                .csrf().disable();
//
//        return http.build();
//    }


}
