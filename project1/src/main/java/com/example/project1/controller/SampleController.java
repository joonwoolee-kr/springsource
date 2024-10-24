package com.example.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@Controller
public class SampleController {

    // @RequestMapping(path = "/basic", method = RequestMethod.GET)
    // public void basic() {
    // log.info("basic 컨트롤러 동작");
    // }

    @GetMapping("/basic")
    public void basic() {
        log.info("basic 컨트롤러 동작");
    }

}
