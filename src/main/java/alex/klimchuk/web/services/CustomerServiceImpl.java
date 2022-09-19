package alex.klimchuk.web.services;

import alex.klimchuk.web.domain.Customer;
import alex.klimchuk.web.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright Alex Klimchuk (c) 30.11.2019.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
