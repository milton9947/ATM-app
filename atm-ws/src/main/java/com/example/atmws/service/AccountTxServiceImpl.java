package com.example.atmws.service;


import com.example.atmws.dto.AccountTxDto;
import com.example.atmws.repository.AccountTxRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountTxServiceImpl implements AccountTxService {

    private final AccountTxRepository accountTxRepository;
    private final Mapper mapper;

    @Autowired
    public AccountTxServiceImpl(AccountTxRepository accountTxRepository, Mapper mapper) {
        this.accountTxRepository = accountTxRepository;
        this.mapper = mapper;
    }

    @Override
    public List<AccountTxDto> findAll() {
        List<AccountTxDto> accountTxDtos = new ArrayList<>();
        accountTxRepository.findAll()
                .forEach(i->accountTxDtos.add(mapper.map(i, AccountTxDto.class)));

        return accountTxDtos;
    }

    @Override
    public List<AccountTxDto> findAllByAccountName(String accountName) {
        List<AccountTxDto> accountTxDtos = new ArrayList<>();
        accountTxRepository.findAllByAccountName(accountName)
                .forEach(i->accountTxDtos.add(mapper.map(i, AccountTxDto.class)));

        return accountTxDtos;
    }

}
