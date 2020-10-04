package com.archu.citiesrestapi.city;


import com.archu.citiesrestapi.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cities")
@Data
@ToString(exclude = "id")
public class City extends BaseEntity {

    @Id
    private String id;

    private String openWeatherMapId;

    private String name;

    private String state;

    private String country;

    private CityCoordinates coord;
}