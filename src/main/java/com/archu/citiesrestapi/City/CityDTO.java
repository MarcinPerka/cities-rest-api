package com.archu.citiesrestapi.City;

import com.archu.citiesrestapi.Base.BaseDTO;
import lombok.Data;
import org.springframework.data.mongodb.core.index.TextIndexed;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CityDTO extends BaseDTO {

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 255, message = "Not valid name - size min 3 and max 255 characters.")
    @TextIndexed
    private String name;

    @NotBlank(message = "Country code is required.")
    @Size(min = 2, max = 2, message = "Not valid country code - size min 2 and max 2 characters.")
    private String country;

    @NotNull(message = "Coordinates are required.")
    @Valid
    private CityCoordinates coord;
}