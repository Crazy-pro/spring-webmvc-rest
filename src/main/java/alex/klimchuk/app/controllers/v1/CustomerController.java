package alex.klimchuk.app.controllers.v1;

import alex.klimchuk.app.api.v1.model.CustomerDto;
import alex.klimchuk.app.api.v1.model.CustomerListDto;
import alex.klimchuk.app.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@Api("This is my Customer Controller")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This will get a list of customers.", notes = "These are some notes about the API.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDto getListOfCustomers(){
        return new CustomerListDto(customerService.getAllCustomers());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createNewCustomer(@RequestBody CustomerDto customerDto){
        return customerService.createNewCustomer(customerDto);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto){
        return customerService.saveCustomerByDto(id, customerDto);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto){
        return customerService.patchCustomer(id, customerDto);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }

}
