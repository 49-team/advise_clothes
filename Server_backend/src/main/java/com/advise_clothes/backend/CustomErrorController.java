package com.advise_clothes.backend;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
//    public String handleError() {
//        return "index.html";
//    }
    public String handleError() {
        return "삐빅 실패입니다.";
    }

}
