package alex.klimchuk.spring.webmvc.rest.repositories;

import alex.klimchuk.spring.webmvc.rest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
