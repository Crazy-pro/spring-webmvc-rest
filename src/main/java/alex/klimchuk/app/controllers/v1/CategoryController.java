package alex.klimchuk.app.controllers.v1;

import alex.klimchuk.app.api.v1.model.CategoryDto;
import alex.klimchuk.app.api.v1.model.CategoryListDto;
import alex.klimchuk.app.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDto getAllCategories() {
        return new CategoryListDto(categoryService.getAllCategories());
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }

}
