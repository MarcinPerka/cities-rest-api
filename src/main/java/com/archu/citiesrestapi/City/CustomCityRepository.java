package com.archu.citiesrestapi.City;

import reactor.core.publisher.Flux;

public interface CustomCityRepository {

    Flux<City> findAllByTextSearch(String searchText);

}
