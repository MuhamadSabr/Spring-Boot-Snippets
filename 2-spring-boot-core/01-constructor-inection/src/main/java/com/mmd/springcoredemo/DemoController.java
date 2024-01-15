package com.mmd.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //Define a private field for the dependency
    private Coach myCoach;//We never create an object of CricketCoach,but through auto-wiring we get it. It is the only implementation

    //Define a constructor for dependency injection                                                                 of Coach
    @Autowired //Tells Spring, inject a dependency.
    public DemoController(Coach theCoach){
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
