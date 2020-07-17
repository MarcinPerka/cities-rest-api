package com.archu.citiesrestapi.City;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Flux<City> getAllCities() {
        log.info("Try to find all cities");
        return cityRepository.findAll();
    }

    public Flux<City> getAllCitiesByNameTextSearch(String name) {
        log.info("Try to find all cities by name text search");
        return cityRepository.findAllByNameTextSearch(name);
    }

    public Mono<City> getCityById(String id) {
        log.info("Try to find city by id {}", id);
        return cityRepository.findById(id);
    }

    public Mono<City> updateCity(City city) {
        log.info("Try to update city with id {}, new params: {}", city.getId(), city.toString());
        return cityRepository.insert(city);
    }

    public void deleteCity(String id) {
        log.info("Try to delete city with id {}", id);
        cityRepository.deleteById(id);
    }

    public Mono<City> createCity(City city) {
        log.info("Try to save city {}", city.toString());
        return cityRepository.save(city);
    }
}
