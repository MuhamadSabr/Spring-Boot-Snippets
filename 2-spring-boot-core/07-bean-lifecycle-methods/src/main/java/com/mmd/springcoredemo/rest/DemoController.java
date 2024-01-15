package com.mmd.springcoredemo.rest;

import com.mmd.springcoredemo.common.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //Define a private field for the dependency
    private Coach myCoach;//We never create an object of CricketCoach,but through auto-wiring we get it. It is the only implementation

    @Autowired
    public DemoController(@Qualifier("tennisCoach")Coach theCoach){

        System.out.println("In constructor " + getClass().getSimpleName());
        myCoach = theCoach;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Calling postConstruct method having a @PostConstruct annotation on it " + getClass().getSimpleName());
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("Calling preDestroy method having a @PreDestroy annotation on it " + getClass().getSimpleName());
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

}
