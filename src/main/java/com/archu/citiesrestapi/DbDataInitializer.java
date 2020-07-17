package com.archu.citiesrestapi;

import com.archu.citiesrestapi.City.City;
import com.archu.citiesrestapi.City.CityRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "mongo", value = "fill-db", havingValue = "true", matchIfMissing = true)
public class DbDataInitializer {

    @Bean
    public CommandLineRunner init(CityRepository cityRepository) {
        return args -> {
            log.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));
            try {
                cityRepository.deleteAll();
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("initial_data/cities.json");
                String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                ObjectMapper objectMapper = new ObjectMapper();
                List<City> cities2Save = objectMapper.readValue(json, new TypeReference<List<City>>() {});
                log.info("Try to fill database with cities. Number of elements: {}", cities2Save.size());
                cityRepository.saveAll(cities2Save);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        };
    }
}