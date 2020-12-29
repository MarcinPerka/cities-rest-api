package com.archu.citiesrestapi.city;

import com.archu.citiesrestapi.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
public class CityDTO extends BaseDTO {

    private String id;

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 255, message = "Not valid name - size min 3 and max 255 characters.")
    private String name;

    @NotEmpty(message = "OpenWeatherMap Id is required.")
    private String openWeatherMapId;

    @Size(max = 255, message = "Not valid name - size min 3 and max 255 characters.")
    private String state;

    @NotBlank(message = "Country code is required.")
    @Size(min = 2, max = 2, message = "Not valid country code - size min 2 and max 2 characters.")
    private String country;

    @NotNull(message = "Coordinates are required.")
    @Valid
    private CityCoordinates coord;
}
