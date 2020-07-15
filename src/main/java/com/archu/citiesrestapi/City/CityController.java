package com.archu.citiesrestapi.City;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/city")
    public Flux<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/city/{id}")
    public Mono<City> getCityById(@PathVariable String id) {
        return cityService.getCityById(id);
    }

    @PutMapping("/city/{id}")
    public Mono<City> updateCity(@RequestBody City city) {
        return cityService.updateCity(city);
    }

    @DeleteMapping("/city/{id}")
    public void deleteCity(@PathVariable String id) {
        cityService.deleteCity(id);
    }

    @PostMapping("/city")
    public Mono<City> createCity(City city) {
        return cityService.createCity(city);
    }
}
