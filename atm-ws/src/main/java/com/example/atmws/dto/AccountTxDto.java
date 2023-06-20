package com.example.atmws.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountTxDto {
    private Long idTx;
    private OperationType operationType;
    private double amount;
    private Timestamp createDate;
}
