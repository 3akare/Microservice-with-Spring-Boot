package com.microservice.accounts.service.impl;

import com.microservice.accounts.constant.AccountsConstants;
import com.microservice.accounts.dto.AccountsDto;
import com.microservice.accounts.dto.CustomerDto;
import com.microservice.accounts.entity.Accounts;
import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.exception.CustomerAlreadyExistsException;
import com.microservice.accounts.exception.ResourceNotFound;
import com.microservice.accounts.mapper.AccountsMapper;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repository.AccountsRepository;
import com.microservice.accounts.repository.CustomerRepository;
import com.microservice.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

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
        Customer customer = CustomerMapper.mapToCustomer(new Customer(), customerDto);
        /* verify the record doesn't already exist */
        verifyRecord(customer);
        /* save a new customer, and create a new account */
        accountsRepository.save(createNewAccount(customerRepository.save(customer)));
    }

    /**
     * @param mobileNumber - String Object
     * @return Account Details
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomer(customer).orElseThrow(
                () -> new ResourceNotFound("Account", "customer", String.valueOf(customer.getCustomerId()))
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountData(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    /**
     * @param mobileNumber - String Object
     * @return boolean indicating if the update was successfully
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        /* search for customer by mobile number */
        Customer customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFound("Customer", "mobileNumber", customerDto.getMobileNumber())
        );

        /* extract account information */
        AccountsDto accountsDto = customerDto.getAccountData();

        /* search for account by customer */
        Accounts accounts = accountsRepository.findByAccountNumber(accountsDto.getAccountNumber()).orElseThrow(
                () -> new ResourceNotFound("Account", "customer", String.valueOf(customer.getCustomerId()))
        );

        if (accounts != null) {
            accountsRepository.save(AccountsMapper.mapToAccount(accounts, accountsDto));
            customerRepository.save(CustomerMapper.mapToCustomer(customer, customerDto));
            isUpdated = true;
        }
        return isUpdated;
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
