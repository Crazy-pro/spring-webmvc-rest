package alex.klimchuk.spring.webmvc.rest.services.impl;

import alex.klimchuk.spring.webmvc.rest.api.v1.mapper.CustomerMapper;
import alex.klimchuk.spring.webmvc.rest.api.v1.model.CustomerDto;
import alex.klimchuk.spring.webmvc.rest.controllers.v1.CustomerController;
import alex.klimchuk.spring.webmvc.rest.domain.Customer;
import alex.klimchuk.spring.webmvc.rest.exceptions.ResourceNotFoundException;
import alex.klimchuk.spring.webmvc.rest.repositories.CustomerRepository;
import alex.klimchuk.spring.webmvc.rest.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
                    customerDto.setCustomerUrl(getCustomerUrl(customer.getId()));

                    return customerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDto)
                .map(customerDto -> {
                    customerDto.setCustomerUrl(getCustomerUrl(id));

                    return customerDto;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {
        return saveAndReturnDto(customerMapper.customerDtoToCustomer(customerDto));
    }

    private CustomerDto saveAndReturnDto(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDto returnDto = customerMapper.customerToCustomerDto(savedCustomer);
        returnDto.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

        return returnDto;
    }

    @Override
    public CustomerDto saveCustomerByDto(Long id, CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        customer.setId(id);

        return saveAndReturnDto(customer);
    }

    @Override
    public CustomerDto patchCustomer(Long id, CustomerDto customerDto) {
        return customerRepository.findById(id).map(customer -> {

                    if (Objects.nonNull(customerDto.getFirstName())) {
                        customer.setFirstName(customerDto.getFirstName());
                    }

                    if (Objects.nonNull(customerDto.getLastName())) {
                        customer.setLastName(customerDto.getLastName());
                    }

                    CustomerDto returnDto = customerMapper.customerToCustomerDto(customerRepository.save(customer));
                    returnDto.setCustomerUrl(getCustomerUrl(id));

                    return returnDto;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    private String getCustomerUrl(Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

}
