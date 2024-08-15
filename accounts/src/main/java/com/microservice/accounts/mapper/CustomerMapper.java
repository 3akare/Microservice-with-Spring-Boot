package com.microservice.accounts.mapper;

import com.microservice.accounts.dto.CustomerDto;
import com.microservice.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDto MapToCustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer MapToCustomer(Customer customer, CustomerDto customerDto){
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
