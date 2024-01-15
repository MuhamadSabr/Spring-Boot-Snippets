package com.mmd.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component //If u don't add this, again there is no way for Spring container to know u mean this to be a Spring bean,
                                                                        //and that it should be available for DI.
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)// New object(bean) instance for each injection
public class TennisCoach implements Coach{

    public TennisCoach(){
        System.out.println("In constructor " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Play tennis for at least 1000 times";
    }
}
