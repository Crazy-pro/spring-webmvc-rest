package alex.klimchuk.app.api.v1.mapper;

import alex.klimchuk.app.api.v1.model.CategoryDto;
import alex.klimchuk.app.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public class CategoryMapperTest {

    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void testCategoryToCategoryDto() {
        Category categoryMock = new Category();
        categoryMock.setName(NAME);
        categoryMock.setId(ID);

        CategoryDto categoryDto = categoryMapper.categoryToCategoryDto(categoryMock);

        assertEquals(Long.valueOf(ID), categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());
    }

}
