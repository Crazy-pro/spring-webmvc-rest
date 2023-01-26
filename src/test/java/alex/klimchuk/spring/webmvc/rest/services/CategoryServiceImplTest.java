package alex.klimchuk.spring.webmvc.rest.services;

import alex.klimchuk.spring.webmvc.rest.api.v1.mapper.CategoryMapper;
import alex.klimchuk.spring.webmvc.rest.api.v1.model.CategoryDto;
import alex.klimchuk.spring.webmvc.rest.domain.Category;
import alex.klimchuk.spring.webmvc.rest.repositories.CategoryRepository;
import alex.klimchuk.spring.webmvc.rest.services.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public class CategoryServiceImplTest {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categoriesMock = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categoriesMock);

        List<CategoryDto> categoryDtos = categoryService.getAllCategories();

        assertEquals(3, categoryDtos.size());
    }

    @Test
    public void testGetCategoryByName() {
        Category categoryMock = new Category();
        categoryMock.setId(ID);
        categoryMock.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(categoryMock);

        CategoryDto categoryDto = categoryService.getCategoryByName(NAME);

        assertEquals(ID, categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());
    }

}
