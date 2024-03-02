package com.advise_clothes.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableJpaAuditing
@SpringBootApplication
public class ServerBackendApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ServerBackendApplication.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
//                 TODO: 개발용, 서버용 따로 나누기
                .allowedOrigins(
                        "http://localhost:3000",
                        "https://web-advise-clothes-front-am952nlt1gbj1t.sel5.cloudtype.app"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

}
