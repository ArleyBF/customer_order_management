package com.BF.customer_order_management.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorDto {

    private HttpStatus status;
    private String message;

}