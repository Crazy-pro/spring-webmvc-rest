package alex.klimchuk.app.repositories;

import alex.klimchuk.app.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
