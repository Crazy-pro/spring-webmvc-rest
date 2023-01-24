package alex.klimchuk.app.repositories;

import alex.klimchuk.app.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
