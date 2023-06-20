package com.example.atmws.service;

import com.example.atmws.dto.AccountTxDto;

import java.util.List;

public interface AccountTxService {
    List<AccountTxDto> findAll();
    List<AccountTxDto> findAllByAccountName(String accountName);
}
