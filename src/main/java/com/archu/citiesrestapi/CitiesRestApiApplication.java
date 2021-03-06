package com.archu.citiesrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CitiesRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitiesRestApiApplication.class, args);
    }

}
