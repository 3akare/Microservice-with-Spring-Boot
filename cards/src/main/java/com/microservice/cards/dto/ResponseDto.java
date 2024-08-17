package com.microservice.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
    name = "Response",
    description = "Schema to hold all successful response information"
)
@Data @AllArgsConstructor
public class ResponseDto {
    @Schema(
        description = "Status code in the response",
        examples = {
                "200", "201",
        }
    )
    private String statusCode;

    @Schema(
        description = "Status message in the response",
        examples = {
                "Request processed successfully"
        }
    )
    private String statusMsg;
}
