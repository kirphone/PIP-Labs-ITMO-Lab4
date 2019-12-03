package com.itmo.lab4.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/api")
public class HelloController {

    @GetMapping(path = "/hello")
    public Object getHelloMessage(){

        return new Object(){
            private String msg = "Hello";

            public String getMsg() {
                return msg;
            }
        };
    }
}
