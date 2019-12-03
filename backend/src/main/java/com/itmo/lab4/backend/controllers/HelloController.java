package com.itmo.lab4.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/hello")
public class HelloController {

    @GetMapping
    public Object getHelloMessage(){

        return new Object(){
            private String msg = "Hello";

            public String getMsg() {
                return msg;
            }
        };
    }
}
