package com.BF.customer_order_management.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException{
    private final HttpStatus httpStatus;
    {
        httpStatus=HttpStatus.BAD_REQUEST;
    }

    public BadRequestException(Long id) {
        super("ID DE CUSTOMER NO VALIDO,SE ENVIO EL ID : " + id);
    }
}
