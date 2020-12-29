package com.archu.citiesrestapi.city;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CityController {

    private final CityService cityService;
    private final CityConverter cityConverter;

    @GetMapping("/cities")
    public Page<CityDTO> getCities(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "size", defaultValue = "10") int size) {
        return cityConverter.createFromEntities(cityService.getCities(page, size));
    }

    @GetMapping("/cities/regex")
    public List<CityDTO> getAllCitiesByTextRegex(@RequestParam(value = "text") String text,
                                                 @RequestParam(value = "size", defaultValue = "10") int size) {
        return cityConverter.createFromEntities(cityService.getAllCitiesByTextRegex(text, size));
    }

    @GetMapping("/cities/{id}")
    public Optional<CityDTO> getCity(@PathVariable String id) {
        return cityService.getCity(id).map(cityConverter::createFrom);
    }
}
