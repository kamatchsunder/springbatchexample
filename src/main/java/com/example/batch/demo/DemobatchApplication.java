package com.example.batch.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableBatchProcessing
public class DemobatchApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemobatchApplication.class, args);
    }

}
