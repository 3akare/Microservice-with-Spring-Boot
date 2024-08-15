package com.microservice.accounts.service;

import com.microservice.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
    * @param customerDto - CustomerDto Object
    */
    void createAccount(CustomerDto customerDto);
}
