package com.example.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 3:27 PM 3/4/2019
 */
@RestController
@RequestMapping("/demo/")
public class demoController {
    @GetMapping("hello")
    public String hello(){
        return "hello world!";
    }
}
