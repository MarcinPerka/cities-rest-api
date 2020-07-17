package com.archu.citiesrestapi.City;

import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import reactor.core.publisher.Flux;

public class CustomCityRepositoryImpl implements CustomCityRepository {

    private final ReactiveMongoOperations mongoOperations;

    public CustomCityRepositoryImpl(ReactiveMongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Flux<City> findAllByNameTextSearch(String name) {
        TextQuery textQuery = TextQuery.queryText(new TextCriteria().matchingAny(name)).sortByScore();
        return mongoOperations.find(textQuery, City.class);
    }
}