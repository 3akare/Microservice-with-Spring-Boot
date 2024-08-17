package com.microservice.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to hold error response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    @Schema(
            name = "API path invoked by client",
            example = "/api/v1/accounts/fetch"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error that happened",
            examples = {
                    "400", "501",
            }
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message representing the error that happened"
    )
    private String errorMsg;

    @Schema(
            description = "Time error happened"
    )
    private LocalDateTime errorTime;
}
