package com.archu.citiesrestapi.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityCoordinates {

    @NotEmpty(message = "Latitude is required.")
    @DecimalMax(value = "90.0", message = "Latitudes range from -90 to 90.")
    @DecimalMin(value = "-90.0", message = "Latitudes range from -90 to 90.")
    private BigDecimal lat;

    @NotEmpty(message = "Longitude is required.")
    @DecimalMax(value = "80.0", message = "Longitudes range from -180 to 80.")
    @DecimalMin(value = "-180.0", message = "Longitudes range from -180 to 80")
    private BigDecimal lon;
}
