package com.microservice.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer, Account, Card, and Loan details"
)
@Data
public class CustomerDetailsDto {
    @Schema(
            description = "Account and Customer details"
    )
    private CustomerDto customerData;

    @Schema(
            description = "Cards details of the customer"
    )
    private CardsDto cardsData;

    @Schema(
            description = "Loans details of the customer"
    )
    private LoansDto loansData;
}
