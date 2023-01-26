package alex.klimchuk.spring.webmvc.rest.api.v1.mapper;

import alex.klimchuk.spring.webmvc.rest.api.v1.model.CategoryDto;
import alex.klimchuk.spring.webmvc.rest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);

}
