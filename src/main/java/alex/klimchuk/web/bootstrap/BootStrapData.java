package alex.klimchuk.web.bootstrap;

import alex.klimchuk.web.domain.Customer;
import alex.klimchuk.web.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Copyright Alex Klimchuk (c) 30.11.2019.
 */
@Slf4j
@Component
public class BootStrapData implements CommandLineRunner {
    
    private final CustomerRepository customerRepository;

    public BootStrapData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) {
        log.info("Loaded Customers!");

        Customer customer1 = new Customer();
        customer1.setFirstName("Alex");
        customer1.setLastName("Klimchuk");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Vlad");
        customer2.setLastName("Skibon");
        customerRepository.save(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Max");
        customer3.setLastName("True");
        customerRepository.save(customer3);

        log.info("Customers Saved: " + customerRepository.count());
    }

}
