package com.advise_clothes.project_advise_clothes.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String main() {
        return "안녕하세요";
    }

    @GetMapping("/db")
    public String db_test() {
        return "Success!";
    }
}
