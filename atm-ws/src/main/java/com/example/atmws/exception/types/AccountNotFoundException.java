package com.example.atmws.exception.types;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class AccountNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private List<String> errors;

    public AccountNotFoundException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
