package com.microservice.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account Information"
)
@Data
public class AccountsDto {

    @Schema(
            description = "Account number of bank account"
    )
    @NotNull(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of bank account",
            example = "Saving"
    )
    @NotNull(message = "Account type cannot be null or empty")
    private String accountType;

    @Schema(
            description = "bank branch Address"
    )
    @NotNull(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
