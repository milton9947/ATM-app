package com.example.atmws.exception.types;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class NotFundsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFundsException(String message) {
        super(message);
    }
}
