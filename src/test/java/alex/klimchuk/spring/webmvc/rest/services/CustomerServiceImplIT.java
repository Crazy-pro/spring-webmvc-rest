package alex.klimchuk.spring.webmvc.rest.services;

import alex.klimchuk.spring.webmvc.rest.api.v1.mapper.CustomerMapper;
import alex.klimchuk.spring.webmvc.rest.api.v1.model.CustomerDto;
import alex.klimchuk.spring.webmvc.rest.bootstrap.BootStrapData;
import alex.klimchuk.spring.webmvc.rest.domain.Customer;
import alex.klimchuk.spring.webmvc.rest.repositories.CategoryRepository;
import alex.klimchuk.spring.webmvc.rest.repositories.CustomerRepository;
import alex.klimchuk.spring.webmvc.rest.repositories.VendorRepository;
import alex.klimchuk.spring.webmvc.rest.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("Loading Customer Data:");
        System.out.println(customerRepository.findAll().size());

        BootStrapData bootStrapData = new BootStrapData(categoryRepository, customerRepository, vendorRepository);
        bootStrapData.run();

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void testPatchCustomerUpdateFirstName() {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(updatedName);

        customerService.patchCustomer(id, customerDto);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstName());
        assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
        assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
    }

    @Test
    public void testPatchCustomerUpdateLastName() {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDto customerDto = new CustomerDto();
        customerDto.setLastName(updatedName);

        customerService.patchCustomer(id, customerDto);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getLastName());
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
        assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
    }

    private Long getCustomerIdValue(){
        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers Found: " + customers.size());

        return customers.get(0).getId();
    }

}
