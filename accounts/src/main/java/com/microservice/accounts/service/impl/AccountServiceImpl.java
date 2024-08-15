package com.microservice.accounts.service.impl;

import com.microservice.accounts.constant.AccountsConstants;
import com.microservice.accounts.controller.AccountsController;
import com.microservice.accounts.dto.CustomerDto;
import com.microservice.accounts.entity.Accounts;
import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.exception.CustomerAlreadyExistsException;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repository.AccountsRepository;
import com.microservice.accounts.repository.CustomerRepository;
import com.microservice.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        /* extract Customer entity from customerDTO */
        Customer customer = CustomerMapper.MapToCustomer(new Customer(), customerDto);
        /* verify the record doesn't already exist */
        verifyRecord(customer);
        /* save the entity */
        Customer savedCustomer = customerRepository.save(customer);
        /* create and save a new account */
        accountsRepository.save(createNewAccount(customer));
    }

    private Accounts createNewAccount(Customer customer){
        Accounts account = new Accounts();
        Long randomNumber = 1000000000L + new Random().nextInt(900000000);

        account.setCustomer(customer);
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);
        account.setAccountNumber(randomNumber);

        return account;
    }

    private void verifyRecord(Customer customer){
        /* customer mobile number */
        if(customerRepository.findByMobileNumber(customer.getMobileNumber()).isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with this number: " + customer.getMobileNumber());
        };
    }
}
