package com.mmd.demo1.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RestCotrollerClass {
    @GetMapping("/") // Exposing / To handle http GET requests
    public String greetings(){
        return "Hello World";
    }
    @GetMapping("/fortune") // Exposing /workout
    public String getDailyFortune(){
        return "I don't know why u r doing this";
    }

}
