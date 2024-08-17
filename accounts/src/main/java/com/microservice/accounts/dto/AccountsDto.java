package com.microservice.accounts.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @NotNull(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @NotNull(message = "Account type cannot be null or empty")
    private String accountType;

    @NotNull(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
