package alex.klimchuk.app.bootstrap;

import alex.klimchuk.app.domain.*;
import alex.klimchuk.app.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@Slf4j
@Component
public class BootStrapData implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public BootStrapData(CategoryRepository categoryRepository,
                         CustomerRepository customerRepository,
                         VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");
        categoryRepository.save(fruits);

        Category dried = new Category();
        dried.setName("Dried");
        categoryRepository.save(dried);

        Category fresh = new Category();
        fresh.setName("Fresh");
        categoryRepository.save(fresh);

        Category exotic = new Category();
        exotic.setName("Exotic");
        categoryRepository.save(exotic);

        Category nuts = new Category();
        nuts.setName("Nuts");
        categoryRepository.save(nuts);

        log.info("Categories Loaded: " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer customer1 = Customer.builder()
                .id(1L)
                .firstName("Alex")
                .lastName("Klimchuk")
                .build();
        customerRepository.save(customer1);

        Customer customer2 = Customer.builder()
                .id(2L)
                .firstName("Vlad")
                .lastName("Skibon")
                .build();
        customerRepository.save(customer2);

        Customer customer3 = Customer.builder()
                .id(3L)
                .firstName("Max")
                .lastName("True")
                .build();
        customerRepository.save(customer3);

        log.info("Customers Loaded: " + customerRepository.count());
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);

        log.info("Vendors Loaded: " + vendorRepository.count());
    }

}
