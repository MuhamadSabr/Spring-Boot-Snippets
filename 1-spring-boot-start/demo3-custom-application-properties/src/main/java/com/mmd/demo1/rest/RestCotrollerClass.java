package com.mmd.demo1.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RestCotrollerClass {
    @Value("${team.name}")
    private String teamName;
    @Value("${coach.name}")
    private String coachName;
    @GetMapping("/") // Exposing / To handle http GET requests
    public String greetings(){
        return "Hello " + coachName + " and " + teamName;
    }
    @GetMapping("/fortune") // Exposing /workout
    public String getDailyFortune(){
        return "I don't know why " + coachName + " makes " + teamName + " do this";
    }

}
