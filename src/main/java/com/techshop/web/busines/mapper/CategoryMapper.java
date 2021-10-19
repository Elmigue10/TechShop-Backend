package com.techshop.web.busines.mapper;

import com.techshop.web.model.dto.CategoryDto;
import com.techshop.web.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CategoryMapper extends GenericMapper<Category, CategoryDto>{
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
}
