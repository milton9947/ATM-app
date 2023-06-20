package com.example.atmws.service;

import com.example.atmws.dto.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> findAll();
    AccountDto getByName(String accountName);
}
