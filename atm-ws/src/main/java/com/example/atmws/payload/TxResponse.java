package com.example.atmws.payload;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TxResponse {
    private String accountName;
    private double balance;
    private Timestamp updateDate;
}
