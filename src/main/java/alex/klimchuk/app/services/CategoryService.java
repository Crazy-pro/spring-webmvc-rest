package alex.klimchuk.app.services;

import alex.klimchuk.app.api.v1.model.CategoryDto;

import java.util.List;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryByName(String name);

}
