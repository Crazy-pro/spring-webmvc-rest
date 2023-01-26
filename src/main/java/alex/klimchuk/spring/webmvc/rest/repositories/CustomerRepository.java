package alex.klimchuk.spring.webmvc.rest.repositories;

import alex.klimchuk.spring.webmvc.rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
