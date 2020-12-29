package com.archu.citiesrestapi.city;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public Page<City> getCities(int page, int size) {
        log.info("Try to find all cities");
        return cityRepository.findAll(PageRequest.of(page, size, Sort.Direction.ASC, "name"));
    }

    public List<City> getAllCitiesByTextRegex(String searchText, int size) {
        String regex = "^" + searchText + ".*";
        log.info("Try to find all cities by text regex {}", regex);
        Pageable pageable = PageRequest.of(0, size);
        return Stream.of(cityRepository.findCitiesByNameRegex(regex, pageable), cityRepository.findCitiesByCountryRegex(regex, pageable))
                .flatMap(x -> x).limit(size).collect(Collectors.toList());
    }

    public Optional<City> getCity(String id) {
        log.info("Try to find city by id {}", id);
        return cityRepository.findCityById(id);
    }
}
