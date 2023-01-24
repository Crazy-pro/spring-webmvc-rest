package alex.klimchuk.app.services;

import alex.klimchuk.app.api.v1.model.CustomerDto;

import java.util.List;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto createNewCustomer(CustomerDto customerDto);

    CustomerDto saveCustomerByDto(Long id, CustomerDto customerDto);

    CustomerDto patchCustomer(Long id, CustomerDto customerDto);

    void deleteCustomerById(Long id);

}
