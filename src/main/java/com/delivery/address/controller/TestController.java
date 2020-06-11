package com.delivery.address.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testDelivery")
public class TestController {

    @GetMapping("/test")
    String testMethod(){
        return "Hello World";
    }
}
