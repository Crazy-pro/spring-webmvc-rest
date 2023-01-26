package alex.klimchuk.spring.webmvc.rest.services;

import alex.klimchuk.spring.webmvc.rest.api.v1.model.CategoryDto;

import java.util.List;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryByName(String name);

}
