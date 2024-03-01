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
                // 2023년
//                .allowedOrigins("http://52.79.195.60",
//                        "http://ec2-52-79-195-60.ap-northeast-2.compute.amazonaws.com",
//                        "http://localhost:3000")
                // 2024년
                // TODO: 개발용, 서버용 따로 나누기
                .allowedOrigins("https://web-advise-clothes-front-am952nlt1gbj1t.sel5.cloudtype.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        // .allowedOrigins만 막 추가하다가 에러가 났다. 마지막 설정 값(localhost:3000였음)을 가져오나보다
        // 기본 포트는 안 붙여도 됨 (http - 80, https - 443)
        // 도메인이랑 ip랑 따로 설정해줘야 됨 (request origin은 브라우저에 입력된 hostname을 따라간다.
    }

}
