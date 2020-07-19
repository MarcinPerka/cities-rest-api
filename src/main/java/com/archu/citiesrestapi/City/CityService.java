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

    public Flux<City> getAllCities(long page, long size) {
        log.info("Try to find all cities");
        return cityRepository.findAll().skip(page * size).take(size);
    }

    public Flux<City> getAllCitiesByTextRegex(String searchText, long page, long size) {
        log.info("Try to find all cities by text regex");
        String regex = "^" + searchText;
        return cityRepository.findCitiesByNameRegex(regex).concatWith(cityRepository.findCitiesByCountryRegex(regex)).skip(page * size).take(size);
    }

    public Mono<City> getCityById(String id) {
        log.info("Try to find city by id {}", id);
        return cityRepository.findById(id);
    }

    //TODO dodanie id / wyszukanie po id / tylko nie nullowe wartosci
    public Mono<City> updateCity(String id, City city) {
        log.info("Try to update city with id {}", city.getId());
        return cityRepository.save(city);
    }

    public Mono<Void> deleteCity(String id) {
        log.info("Try to delete city with id {}", id);
        return cityRepository.deleteById(id);
    }

    public Mono<City> createCity(City city) {
        log.info("Try to save city {}", city.toString());
        return cityRepository.save(city);
    }
}
