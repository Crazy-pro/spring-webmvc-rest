package alex.klimchuk.web.repositories;

import alex.klimchuk.web.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright Alex Klimchuk (c) 30.11.2019.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
