package com.example.atmws.exception.types;

import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@NoArgsConstructor
public class InvalidDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private BindingResult bindingResult;

    public InvalidDataException(BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
