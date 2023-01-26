package alex.klimchuk.spring.webmvc.rest.services.impl;

import alex.klimchuk.spring.webmvc.rest.api.v1.mapper.CategoryMapper;
import alex.klimchuk.spring.webmvc.rest.api.v1.model.CategoryDto;
import alex.klimchuk.spring.webmvc.rest.repositories.CategoryRepository;
import alex.klimchuk.spring.webmvc.rest.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.findByName(name));
    }

}
