package com.techshop.web.busines.mapper;


import java.util.List;
import java.util.Optional;

public interface GenericMapper <E,D> {

    E toEntity(D dto);
    E toEntityOption(Optional<D>  dto);
    D toDto(E entity);
    D toDtoOption(Optional<E> entity);
    List<E> toEntityList(List<D> dtoList);
    List<D> toDtoList(List<E> entityList);


}
