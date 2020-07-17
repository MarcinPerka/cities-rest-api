package com.archu.citiesrestapi.City;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CityController {

    private final CityService cityService;
    private final CityConverter cityConverter;

    public CityController(CityService cityService, CityConverter cityConverter) {
        this.cityService = cityService;
        this.cityConverter = cityConverter;
    }

    @GetMapping("/cities")
    public Flux<CityDTO> getAllCities() {
        return cityConverter.createFromEntities(cityService.getAllCities());
    }

    @GetMapping("/cities/search/findByName")
    public Flux<CityDTO> getAllCitiesByNameTextSearch(@RequestParam String name) {
        return cityConverter.createFromEntities(cityService.getAllCitiesByNameTextSearch(name));
    }

    @GetMapping("/cities/{id}")
    public Mono<CityDTO> getCityById(@PathVariable String id) {
        return cityService.getCityById(id).map(cityConverter::createFrom);
    }

    @PutMapping("/cities/{id}")
    public Mono<CityDTO> updateCity(@PathVariable String id, @RequestBody @Valid CityDTO cityDTO) {
        return cityService.updateCity(id, cityConverter.createFrom(cityDTO)).map(cityConverter::createFrom);
    }

    @DeleteMapping("/cities/{id}")
    public Mono<Void> deleteCity(@PathVariable String id) {
        return cityService.deleteCity(id);
    }

    @PostMapping("/cities")
    public Mono<CityDTO> createCity(@RequestBody @Valid CityDTO cityDTO) {
        return cityService.createCity(cityConverter.createFrom(cityDTO)).map(cityConverter::createFrom);
    }
}
