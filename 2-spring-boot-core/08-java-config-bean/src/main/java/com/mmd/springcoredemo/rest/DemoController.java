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
    public DemoController(@Qualifier("SwimCoach")Coach theCoach){//First letter being small is only specific to classes annotated with @Component.

        System.out.println("In constructor " + getClass().getSimpleName());//For bean user defined id u have to type it the way the bean ID is specified.
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

}
