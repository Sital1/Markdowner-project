package com.markdown.auth;


import io.micrometer.core.instrument.config.validate.Validated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Component;

@EnableMongoAuditing
@SpringBootApplication
//@EnableMongoRepositories
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class AuthApplication {

//    private static Environment env;
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);

//        System.out.println(env.getProperty("password"));


    }

}
