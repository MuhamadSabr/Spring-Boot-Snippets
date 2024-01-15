package com.mmd.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component //Marks the class as a Spring Bean, and makes it available for dependency injection.
//Manged by Ioc container. Is a Spring-managed component.
//When the Spring app starts, the IoC container creates instances of the managed beans(CricketCoach) resolves their dependencies, n manages their life cycle

@Primary //This is the primary implementation among the candidates for Coach interface. When no qualifying bean is given, this will be used.
                                                                  //But be careful, there must be only one @Primary implementation if no qualifying bean is given
public class CricketCoach implements Coach{

    public CricketCoach(){
        System.out.println("In constructor " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout(){
        return "Practice fast bowling for 15 minutes.";
    }
}
