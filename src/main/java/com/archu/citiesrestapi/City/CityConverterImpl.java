package com.archu.citiesrestapi.City;

import org.springframework.stereotype.Component;

@Component
public class CityConverterImpl implements CityConverter {

    @Override
    public City createFrom(CityDTO dto) {
        City city = new City();
        return updateEntity(city, dto);
    }

    @Override
    public CityDTO createFrom(City entity) {
        CityDTO dto = new CityDTO();
        if(entity != null){
            dto.setName(entity.getName());
            dto.setCountry(entity.getCountry());
            dto.setCoord(entity.getCoord());
        }
        return dto;
    }

    @Override
    public City updateEntity(City entity, CityDTO dto) {
        if (entity != null && dto != null) {
            entity.setName(dto.getName());
            entity.setCountry(dto.getCountry());
            entity.setCoord(dto.getCoord());
        }
        return entity;
    }
}
