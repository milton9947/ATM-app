package com.example.atmws.exception;

import com.example.atmws.exception.types.AccountNotFoundException;
import com.example.atmws.exception.types.InvalidDataException;
import com.example.atmws.exception.types.NotFundsException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AtmExceptionControllerAdvice {

    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity<ExceptionResponse> invalidDataException(InvalidDataException exception) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return buildResponseException(HttpStatus.BAD_REQUEST, new InvalidDataException("Invalid sent data"), errors);
    }

    @ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<ExceptionResponse> accountNotFoundException(AccountNotFoundException exception) {
        return buildResponseException(HttpStatus.NOT_FOUND, exception, exception.getErrors());
    }

    @ExceptionHandler(value = NotFundsException.class)
    public ResponseEntity<ExceptionResponse> notFundsException(NotFundsException exception) {
        return buildResponseException(HttpStatus.BAD_REQUEST, exception, null);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ExceptionResponse> nullPointerException(NullPointerException exception) {
        return buildResponseException(HttpStatus.INTERNAL_SERVER_ERROR, exception, null);
    }

    private ResponseEntity<ExceptionResponse> buildResponseException(
            HttpStatus httpStatus, Exception exception, List<String> exceptions){
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                .statusCode(httpStatus.value())
                .message(exception.getMessage())
                .date(new Date())
                .errors(exceptions)
                .build(),
                httpStatus
        );
    }
}
