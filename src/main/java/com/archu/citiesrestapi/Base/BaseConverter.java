package com.archu.citiesrestapi.Base;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface BaseConverter<D extends BaseDTO, E extends BaseEntity> {

    E createFrom(D dto);

    D createFrom(E entity);

    E updateEntity(E entity, D dto);

    default List<D> createFromEntities(final Collection<E> entities) {
        return entities.stream()
                .map(this::createFrom)
                .collect(Collectors.toList());
    }

    default List<E> createFromDtos(final Collection<D> dtos) {
        return dtos.stream()
                .map(this::createFrom)
                .collect(Collectors.toList());
    }

    default Page<D> createFromEntities(final Page<E> entities) {
        return entities.map(this::createFrom);
    }

    default Page<E> createFromDtos(final Page<D> dtos) {
        return dtos.map(this::createFrom);
    }
}