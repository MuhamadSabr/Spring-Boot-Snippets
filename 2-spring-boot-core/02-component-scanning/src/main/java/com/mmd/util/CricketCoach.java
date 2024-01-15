package com.mmd.util;

import org.springframework.stereotype.Component;

@Component //Marks the class as a Spring Bean, and makes it available for dependency injection.
//Manged by Ioc container. Is a Spring-managed component.
//When the Spring app starts, the IoC container creates instances of the managed beans(CricketCoach) resolves their dependencies, n manages their life cycle
public class CricketCoach implements Coach{
    @Override
    public String getDailyWorkout(){
        return "Practice fast bowling for 15 minutes.";
    }
}
