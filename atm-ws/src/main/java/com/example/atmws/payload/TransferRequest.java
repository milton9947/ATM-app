package com.example.atmws.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TransferRequest {

    @NotBlank(message = "The accountNameSource value cannot be Null")
    private String accountNameSource;

    @NotBlank(message = "The accountNameDestination value cannot be Null")
    private String accountNameDestination;

    @Min(value = 1, message = "The amount cannot be zero or negative")
    private double amount;
}
