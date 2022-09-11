package alex.klimchuk.web.services;

import alex.klimchuk.web.domain.Customer;

import java.util.List;

/**
 * Copyright Alex Klimchuk (c) 2019.
 */
public interface CustomerService {

    List<Customer> findAllCustomers();

    Customer findCustomerById(Long id);

    Customer saveCustomer(Customer customer);

}
