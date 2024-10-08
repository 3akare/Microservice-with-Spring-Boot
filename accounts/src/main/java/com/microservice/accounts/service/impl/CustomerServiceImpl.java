package com.microservice.accounts.service.impl;

import com.microservice.accounts.client.CardFeignClient;
import com.microservice.accounts.client.LoansFeignClient;
import com.microservice.accounts.dto.*;
import com.microservice.accounts.entity.Accounts;
import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.exception.ResourceNotFound;
import com.microservice.accounts.mapper.AccountsMapper;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repository.AccountsRepository;
import com.microservice.accounts.repository.CustomerRepository;
import com.microservice.accounts.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardFeignClient cardFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomer(customer).orElseThrow(
                () -> new ResourceNotFound("Account", "customer", String.valueOf(customer.getCustomerId()))
        );
        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccountData(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        customerDetailsDto.setCustomerData(CustomerMapper.mapToCustomerDto(customer, customerDto));

        ResponseEntity<LoansDto> loansDetails = loansFeignClient.fetchLoanDetails(mobileNumber);
        ResponseEntity<CardsDto> cardDetails = cardFeignClient.fetchCardDetails(mobileNumber);

        customerDetailsDto.setLoansData(loansDetails.getBody());
        customerDetailsDto.setCardsData(cardDetails.getBody());

        return customerDetailsDto;
    }
}
