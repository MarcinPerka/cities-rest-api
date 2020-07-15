package com.archu.citiesrestapi.City;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "mongo", value = "fill-db", havingValue = "true", matchIfMissing = true)
public class FillDbRunner implements CommandLineRunner {

    private final CityRepository cityRepository;

    public FillDbRunner(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(String...args) {
        log.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("initial_data/cities.json");
            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            List<City> cities2Save = objectMapper.readValue(json, new TypeReference<List<City>>(){});
            cities2Save.forEach(city -> {
                log.info("Try to fill database with city {}", city.toString());
                cityRepository.save(city);

            });
        } catch(Exception e){
            log.error(e.getMessage());
        }

    }
}