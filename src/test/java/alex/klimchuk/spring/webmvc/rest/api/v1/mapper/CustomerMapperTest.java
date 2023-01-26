package alex.klimchuk.spring.webmvc.rest.api.v1.mapper;

import alex.klimchuk.spring.webmvc.rest.api.v1.model.CustomerDto;
import alex.klimchuk.spring.webmvc.rest.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public class CustomerMapperTest {

    public static final String FIRST_NAME = "Jimmy";
    public static final String LAST_NAME = "Fallon";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void testCustomerToCustomerDto() {
        Customer customerMock = new Customer();
        customerMock.setFirstName(FIRST_NAME);
        customerMock.setLastName(LAST_NAME);

        CustomerDto customerDto = customerMapper.customerToCustomerDto(customerMock);

        assertEquals(FIRST_NAME, customerDto.getFirstName());
        assertEquals(LAST_NAME, customerDto.getLastName());
    }

}
