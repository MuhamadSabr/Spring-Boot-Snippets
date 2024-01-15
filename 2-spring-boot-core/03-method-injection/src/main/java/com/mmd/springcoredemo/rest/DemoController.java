package com.mmd.springcoredemo.rest;

import com.mmd.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //Define a private field for the dependency
    private Coach myCoach;//We never create an object of CricketCoach,but through auto-wiring we get it. It is the only implementation

    //Define a method for dependency injection                                                                 of Coach
    @Autowired
    public void setMyCoach(Coach theCoach){//U always have to use @Autowired annotation even if u only have one method
        myCoach = theCoach;                                                         //that u intend to use as Autowired.
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
