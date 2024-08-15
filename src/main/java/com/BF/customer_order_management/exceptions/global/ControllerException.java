package com.BF.customer_order_management.exceptions.global;


import com.BF.customer_order_management.dto.ErrorDto;
import com.BF.customer_order_management.exceptions.BadRequestException;
import com.BF.customer_order_management.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> runtimeExceptionHandler(RuntimeException ex){
        ErrorDto error = ErrorDto.builder()
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorDto> handleBadRequestException(BadRequestException e){
        ErrorDto errorDto = ErrorDto.builder()
                .message(e.getMessage())
                .status(e.getHttpStatus())
                .build();
        return ResponseEntity.status(e.getHttpStatus()).body(errorDto);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException e){
        ErrorDto errorDto = ErrorDto.builder()
                .message(e.getMessage())
                .status(e.getHttpStatus())
                .build();
        return ResponseEntity.status(e.getHttpStatus()).body(errorDto);
    }
}

