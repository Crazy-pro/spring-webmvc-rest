package alex.klimchuk.app.api.v1.mapper;

import alex.klimchuk.app.api.v1.model.CustomerDto;
import alex.klimchuk.app.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);

}
