package com.spring.cloud.example.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/server")
public class ServerController {

    @GetMapping("/msg")
    public String msg(){
        String msg = "this is a product message 8080";
        log.info(msg);
        return msg;
    }
}
