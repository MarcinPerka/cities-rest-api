package com.archu.citiesrestapi.bootstrap;

import com.archu.citiesrestapi.city.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
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
import java.util.Objects;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "mongo", value = "city-bootstrap", havingValue = "true", matchIfMissing = true)
@AllArgsConstructor
public class CityBootstrap implements ApplicationRunner {


    private final CityRepository cityRepository;
    private final CityConverterImpl cityConverter;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("initial_data/cities.json");
        String json = IOUtils.toString(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<CityDTO>> typeReference = new TypeReference<>() {
        };
        List<CityDTO> citiesDto2Save = objectMapper.readValue(json, typeReference);
        log.info("Try to fill database with cities. Number of elements: {}", citiesDto2Save.size());
        cityRepository.saveAll(cityConverter.createFromDtos(citiesDto2Save));
    }
}