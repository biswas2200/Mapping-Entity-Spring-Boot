package com.mappings.manytomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mappings.manytomany.dao")
public class ManyToManyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManyToManyApplication.class, args);
    }

}
