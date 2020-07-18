package com.archu.citiesrestapi.City;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CityController {

    private final CityService cityService;
    private final CityConverter cityConverter;

    public CityController(CityService cityService, CityConverter cityConverter) {
        this.cityService = cityService;
        this.cityConverter = cityConverter;
    }

    @GetMapping("/cities")
    public Flux<CityDTO> getAllCities() {
        return cityConverter.createFromEntities(cityService.getAllCities()).subscribeOn(Schedulers.parallel());
    }

    @GetMapping("/cities/search")
    public Flux<CityDTO> getAllCitiesByTextSearch(@RequestParam String text) {
        return cityConverter.createFromEntities(cityService.getAllCitiesByTextSearch(text)).subscribeOn(Schedulers.parallel());
    }

    @GetMapping("/cities/{id}")
    public Mono<CityDTO> getCityById(@PathVariable String id) {
        return cityService.getCityById(id).map(cityConverter::createFrom).subscribeOn(Schedulers.parallel());
    }

    @PutMapping("/cities/{id}")
    public Mono<CityDTO> updateCity(@PathVariable String id, @RequestBody @Valid CityDTO cityDTO) {
        return cityService.updateCity(id, cityConverter.createFrom(cityDTO)).map(cityConverter::createFrom).subscribeOn(Schedulers.parallel());
    }

    @DeleteMapping("/cities/{id}")
    public Mono<Void> deleteCity(@PathVariable String id) {
        return cityService.deleteCity(id).subscribeOn(Schedulers.parallel());
    }

    @PostMapping("/cities")
    public Mono<CityDTO> createCity(@RequestBody @Valid CityDTO cityDTO) {
        return cityService.createCity(cityConverter.createFrom(cityDTO)).map(cityConverter::createFrom).subscribeOn(Schedulers.parallel());
    }
}
