package com.advise_clothes.project_advise_clothes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

@SpringBootApplication
//@Configuration
public class ProjectAdviseClothesApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
//        System.setProperty("org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH", "true");
        SpringApplication.run(ProjectAdviseClothesApplication.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://52.79.195.60",
                        "http://ec2-52-79-195-60.ap-northeast-2.compute.amazonaws.com",
                        "http://localhost:3001")
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        // .allowedOrigins만 막 추가하다가 에러가 났다. 마지막 설정 값(localhost:3000였음)을 가져오나보다
        // 기본 포트는 안 붙여도 됨 (http - 80, https - 443)
        // 도메인이랑 ip랑 따로 설정해줘야 됨 (request origin은 브라우저에 입력된 hostname을 따라간다.

    }

    // 세션을 bcrypt로 만들면 /가 포함될 때가 있는데, /도 읽게 하려고 애쓴 흔적
    // 결국 bcrpyt에서 /가 없을 때까지 반복하도록 만들었다.
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        UrlPathHelper urlPathHelper = new UrlPathHelper();
//        urlPathHelper.setUrlDecode(false);
//        configurer.setUrlPathHelper(urlPathHelper);
//    }
}
