package com.archu.citiesrestapi.City;


import com.archu.citiesrestapi.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "cities")
@Data
@ToString(exclude = "id")
public class City extends BaseEntity {

    @Id
    @JsonIgnore
    private String id;

    @NotEmpty(message= "OpenWeatherMap Id is required.")
    @JsonProperty("id")
    private String openWeatherMapId;

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 255, message = "Not valid name - size min 3 and max 255 characters.")
    private String name;

    @Size(max = 255, message = "Not valid name - size min 3 and max 255 characters.")
    private String state;

    @NotBlank(message = "Country code is required.")
    @Size(min = 2, max = 2, message = "Not valid country code - size min 2 and max 2 characters.")
    private String country;

    @NotNull(message = "Coordinates are required.")
    @Valid
    private CityCoordinates coord;
}