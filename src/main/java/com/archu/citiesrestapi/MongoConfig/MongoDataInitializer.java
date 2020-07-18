package com.archu.citiesrestapi.MongoConfig;

import com.archu.citiesrestapi.City.City;
import com.archu.citiesrestapi.City.CityRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "mongo", value = "fill-db", havingValue = "true", matchIfMissing = true)
public class MongoDataInitializer implements ApplicationRunner {


    private final CityRepository cityRepository;

    public MongoDataInitializer(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args.getSourceArgs()));
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("initial_data/city.list.json");
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<City>> typeReference = new TypeReference<List<City>>() {
        };
        List<City> cities2Save = objectMapper.readValue(json, typeReference);
        log.info("Try to fill database with cities. Number of elements: {}", cities2Save.size());
        cityRepository.saveAll(cities2Save).subscribe(city -> log.info("City has been saved: {}", city));
    }
}