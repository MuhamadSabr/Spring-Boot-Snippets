package com.mmd.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component //If u don't add this, again there is no way for Spring container to know u mean this to be a Spring bean,
                                                                        //and that it should be available for DI.
public class TennisCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Play tennis for at least 1000 times";
    }
}
