package com.img.arena.tennis.rest.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args){
        new SpringApplication(Application.class).run(args);
    }
}
