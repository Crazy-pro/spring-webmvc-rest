package alex.klimchuk.app.repositories;

import alex.klimchuk.app.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

}
