package com.archu.citiesrestapi.MongoConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;

import javax.annotation.PostConstruct;

@Configuration
public class MongoConfig {

    private final ReactiveMongoOperations mongoOperations;

    public MongoConfig(ReactiveMongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }

    @PostConstruct
    public void initIndexes() {
        TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder()
                .onField("name")
                .onField("country")
                .build();
        mongoOperations.indexOps("cities").ensureIndex(textIndex);
    }
}