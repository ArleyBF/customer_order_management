package com.BF.customer_order_management.exceptions.global;

import com.BF.customer_order_management.exceptions.BadRequestException;
import com.BF.customer_order_management.exceptions.NotFoundException;
import com.BF.customer_order_management.dto.ErrorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerExceptionTest {

    private ControllerException controllerException;

    @BeforeEach
    public void setUp() {
        controllerException = new ControllerException();
    }

    @Test
    public void testHandleBadRequestException() {
        BadRequestException exception = new BadRequestException(1L);
        ResponseEntity<ErrorDto> response = controllerException.handleBadRequestException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("ID DE CUSTOMER NO VALIDO,SE ENVIO EL ID : 1", response.getBody().getMessage());
    }

    @Test
    public void testHandleNotFoundException() {
        NotFoundException exception = new NotFoundException();
        ResponseEntity<ErrorDto> response = controllerException.handleNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("NO EXISTE EL CLIENTE, VERIFICAR ID", response.getBody().getMessage());
    }

    @Test
    public void testRuntimeExceptionHandler() {
        RuntimeException exception = new RuntimeException("Runtime exception occurred");
        ResponseEntity<ErrorDto> response = controllerException.runtimeExceptionHandler(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Runtime exception occurred", response.getBody().getMessage());
    }
}
