package alex.klimchuk.app.services;

import alex.klimchuk.app.api.v1.mapper.CustomerMapper;
import alex.klimchuk.app.api.v1.model.CustomerDto;
import alex.klimchuk.app.domain.Customer;
import alex.klimchuk.app.repositories.CustomerRepository;
import alex.klimchuk.app.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerService customerService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<CustomerDto> customerDtos = customerService.getAllCustomers();

        assertEquals(2, customerDtos.size());
    }

    @Test
    public void testGetCustomerById() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));

        CustomerDto customerDto = customerService.getCustomerById(1L);

        assertEquals("Michale", customerDto.getFirstName());
    }

    @Test
    public void testCreateNewCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDto.getFirstName());
        savedCustomer.setLastName(customerDto.getLastName());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDto savedDto = customerService.createNewCustomer(customerDto);

        assertEquals(customerDto.getFirstName(), savedDto.getFirstName());
        assertEquals("/api/v1/customers/1", savedDto.getCustomerUrl());
    }

    @Test
    public void testSaveCustomerByDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDto.getFirstName());
        savedCustomer.setLastName(customerDto.getLastName());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDto savedDto = customerService.saveCustomerByDto(1L, customerDto);

        assertEquals(customerDto.getFirstName(), savedDto.getFirstName());
        assertEquals("/api/v1/customers/1", savedDto.getCustomerUrl());
    }

    @Test
    public void testDeleteCustomerById() {
        Long id = 1L;

        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }

}
