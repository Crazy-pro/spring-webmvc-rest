package alex.klimchuk.spring.webmvc.rest.repositories;

import alex.klimchuk.spring.webmvc.rest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

}
