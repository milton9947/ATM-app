package com.example.atmws.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TxRequest {
    @Min(value = 1, message = "The amount cannot be zero or negative")
    private double amount;

    @NotBlank(message = "The accountName value cannot be Null")
    private String accountName;
}
