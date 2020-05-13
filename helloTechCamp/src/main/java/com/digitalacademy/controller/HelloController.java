package com.digitalacademy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello spring boot";
    }

    @GetMapping(path = "/hello/{name}")
    public String getName(@PathVariable String name){
        return "Hello " + name;
    }
}
