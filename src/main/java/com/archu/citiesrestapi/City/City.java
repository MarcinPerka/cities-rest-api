package com.archu.citiesrestapi.City;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Document(collection = "cities")
@Data
@ToString(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    private String id;

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 255, message = "Not valid name - size min 3 and max 255 characters.")
    private String name;

    @NotBlank(message = "Country code is required.")
    @Size(min = 2, max = 2, message = "Not valid country code - size min 2 and max 2 characters.")
    private String country;

    @NotEmpty(message = "Coordinates are required.")
    private CityCoordinates coord;

}