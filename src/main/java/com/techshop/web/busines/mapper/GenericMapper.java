package com.techshop.web.busines.mapper;


import java.util.List;

public interface GenericMapper <E,D> {

    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntityList(List<D> dtoList);
    List<D> toDtoList(List<E> entityList);

}
