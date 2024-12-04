package com.example.datatest.Controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class HomeController {

    @GetMapping("/index")
    public void getHome() {
        log.info("인덱스 요청");
    }

}
