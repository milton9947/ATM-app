package com.example.atmws.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountDto {
    private String accountName;
    private Double balance;
    private Timestamp createDate;
    private Timestamp updateDate;
}
