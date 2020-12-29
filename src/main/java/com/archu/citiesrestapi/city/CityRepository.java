package com.archu.citiesrestapi.city;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface CityRepository extends MongoRepository<City, String> {

    @Query("{ 'name': { $regex: ?0,$options:'ims'} }")
    Stream<City> findCitiesByNameRegex(String regexString, Pageable pageable);

    @Query("{ 'country': { $regex: ?0,$options:'ims'} }")
    Stream<City> findCitiesByCountryRegex(String regexString, Pageable pageable);

    Optional<City> findCityByOpenWeatherMapId(String openWeatherMapId);

    Optional<City> findCityById(String id);
}
