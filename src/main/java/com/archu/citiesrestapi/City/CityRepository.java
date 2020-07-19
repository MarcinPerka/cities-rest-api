package com.archu.citiesrestapi.City;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CityRepository extends ReactiveMongoRepository<City, String> {

    @Query("{ 'name': { $regex: ?0,$options:'i'} }")
    Flux<City> findCitiesByNameRegex(String regexString);

    @Query("{ 'country': { $regex: ?0,$options:'i'} }")
    Flux<City> findCitiesByCountryRegex(String regexString);
}
