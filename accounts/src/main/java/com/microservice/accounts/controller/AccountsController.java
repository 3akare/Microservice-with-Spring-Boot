package com.microservice.accounts.controller;

import com.microservice.accounts.constant.AccountsConstants;
import com.microservice.accounts.dto.AccountsContactDto;
import com.microservice.accounts.dto.CustomerDto;
import com.microservice.accounts.dto.ResponseDto;
import com.microservice.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "CRUD REST APIs Accounts in bank",
    description = "CRUD REST APIs in bank to CREATE, READ, UPDATE and DELETE account details"
)
@RestController
@RequestMapping(value = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {
    public final IAccountService iAccountService;
    private final Environment environment;
    private final AccountsContactDto accountsContactDto;

    @Value("${build.version}")
    private String buildVersion;

    public AccountsController(IAccountService iAccountService, Environment environment, AccountsContactDto accountsContactDto) {
        this.iAccountService = iAccountService;
        this.environment = environment;
        this.accountsContactDto = accountsContactDto;
    }

    @Operation(
        summary = "Create Account REST API",
        description = "REST APIs to create new Customer & Account inside bank"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        /* create customer and account */
        iAccountService.createAccount(customerDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
        summary = "Fetch Account Details REST API",
        description = "REST APIs to fetch Customer & Account details based on mobile number"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status OK"
    )
    @GetMapping
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @RequestParam("mobile_number")
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ){
        /* get customer details */
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iAccountService.fetchAccount(mobileNumber));
    }

    @Operation(
        summary = "Update Account Details REST API",
        description = "REST APIs to update Customer & Account details based on mobile number"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status INTERNAL SERVER ERROR"
        )
    })
    @PatchMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(
            @Valid @RequestBody CustomerDto customerDto
    ){
        /* update customer and account details */
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if (isUpdated)
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200)
            );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500)
        );
    }

    @Operation(
        summary = "Delete Account REST API",
        description = "REST APIs to delete Customer & Account details based on mobile number"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status INTERNAL SERVER ERROR"
        )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(
            @RequestParam("mobile_number")
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ){
        /* delete customer and account */
        if(iAccountService.deleteAccount(mobileNumber))
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200)
            );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500)
        );
    }

    @Operation(
            summary = "Fetch Java Version",
            description = "REST APIs to fetch Java version"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        /* get java version */
        return ResponseEntity.status(HttpStatus.OK).body(
                environment.getProperty("JAVA_HOME")
        );
    }

    @Operation(
            summary = "Fetch Build Version",
            description = "REST APIs to fetch Build version"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion(){
        /* get java version */
        return ResponseEntity.status(HttpStatus.OK).body(
                buildVersion
        );
    }

    @Operation(
            summary = "Fetch Contact Info",
            description = "REST APIs to fetch Contact Info"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactDto> getContactInfo(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountsContactDto);
    }
}
