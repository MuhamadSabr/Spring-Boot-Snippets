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

    /**
     * Injecting team.name n coach.name
     */
    @Value( "${team.name}")
    private String teamName;
    @Value( "${coach.name}" )
    private String coachName;
    //Another endpoint
    @GetMapping("/workout") // Exposing /workout
    public String getDailyWorkout(){
        return "Run for your coach " +coachName + " My " + teamName + " Team";
    }

    @GetMapping("/fortune") // Exposing /workout
    public String getDailyFortune(){
        return "I don't know why u r doing this";
    }

}
