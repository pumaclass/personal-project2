package com.sparta.personalproject2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PersonalProject2Application {

    public static void main(String[] args) {
        SpringApplication.run(PersonalProject2Application.class, args);
    }

}
