package com.microservice.accounts.mapper;

import com.microservice.accounts.dto.AccountsDto;
import com.microservice.accounts.entity.Accounts;

public class AccountsMapper {
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto){
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    };

    public static Accounts mapToAccount(Accounts accounts, AccountsDto accountsDto){
     accounts.setAccountNumber(accountsDto.getAccountNumber());
     accounts.setAccountType(accountsDto.getAccountType());
     accounts.setBranchAddress(accountsDto.getBranchAddress());
     return accounts;
    }
}
