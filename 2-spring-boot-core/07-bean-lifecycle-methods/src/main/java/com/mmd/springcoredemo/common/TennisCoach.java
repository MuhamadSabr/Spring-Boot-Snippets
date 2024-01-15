package com.mmd.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component //If u don't add this, again there is no way for Spring container to know u mean this to be a Spring bean,
                                                                        //and that it should be available for DI.

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)// A shared(bean)instance for each injection. This is the default mode.
public class TennisCoach implements Coach{

    public TennisCoach(){
        System.out.println("In constructor " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Play tennis for at least 1000 times";
    }


    @PostConstruct
    public void postConstruct(){
        System.out.println("Calling postConstruct method having a @PostConstruct annotation on it " + getClass().getSimpleName());
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("Calling preDestroy method having a @PreDestroy annotation on it " + getClass().getSimpleName());
    }

}
