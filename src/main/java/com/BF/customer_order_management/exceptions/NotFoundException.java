package com.BF.customer_order_management.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;
    {
        httpStatus=HttpStatus.NOT_FOUND;
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super("NO EXISTE EL CLIENTE, VERIFICAR ID");
    }
}
