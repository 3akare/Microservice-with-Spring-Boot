package com.microservice.accounts.service;

import com.microservice.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
    /**
     * @param mobileNumber: String
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
