package com.mmd.demo1.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RestCotrollerClass {
    @GetMapping("/") // To handle http GET requests

    public String greetings(){
        return "Hello World";
    }
}
