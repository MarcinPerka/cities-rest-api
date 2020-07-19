package com.archu.citiesrestapi.City;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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
    public Page<CityDTO> getAllCities(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "10") int size) {
        return cityConverter.createFromEntities(cityService.getAllCities(page, size));
    }

    @GetMapping("/cities/regex")
    public List<CityDTO> getAllCitiesByTextRegex(@RequestParam(value = "text") String text,
                                                 @RequestParam(value = "size", defaultValue = "10") int size) {
        return cityConverter.createFromEntities(cityService.getAllCitiesByTextRegex(text, size));
    }

    @GetMapping("/cities/{id}")
    public Optional<CityDTO> getCityById(@PathVariable String id) {
        return cityService.getCityById(id).map(cityConverter::createFrom);
    }
}
