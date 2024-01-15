package com.mmd.springcoredemo.config;

import com.mmd.springcoredemo.common.Coach;
import com.mmd.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("SwimCoach")
    public Coach sCoach(){//The default bean id is the method name. U can change it by give the Id is parameter to the @Bean(arg) annotation.
        return new SwimCoach();
    }
}
