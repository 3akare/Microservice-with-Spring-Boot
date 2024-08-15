package com.microservice.accounts.service;

import com.microservice.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
    * @param customerDto - CustomerDto Object
    */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - String Object
     * @return Account Details
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * @param mobileNumber - String Object
     * @return boolean indicating if the update was successfully
     */
    boolean updateAccount(CustomerDto customerDto);
}
