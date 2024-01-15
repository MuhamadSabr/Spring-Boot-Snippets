package com.mmd.springcoredemo.rest;

import com.mmd.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //Define a private field for the dependency
    private Coach myCoach;//We never create an object of CricketCoach,but through auto-wiring we get it. It is the only implementation
    private Coach anotherCoach;

    @Autowired
    public DemoController(@Qualifier("tennisCoach")Coach theCoach,
                          @Qualifier("tennisCoach")Coach theAnotherCoach){

        System.out.println("In constructor " + getClass().getSimpleName());
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check(){
        return "Bean scope: " + (myCoach == anotherCoach ? "Singleton" : "Prototype");
    }
}
