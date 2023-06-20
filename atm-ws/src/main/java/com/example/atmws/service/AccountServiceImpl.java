package com.example.atmws.service;

import com.example.atmws.dto.AccountDto;
import com.example.atmws.model.Account;
import com.example.atmws.repository.AccountRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final Mapper mapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, Mapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public List<AccountDto> findAll() {
        List<AccountDto> accountDtos = new ArrayList<>();
        accountRepository.findAll()
                .forEach(i->accountDtos.add(mapper.map(i, AccountDto.class)));

        return accountDtos;
    }

    @Override
    public AccountDto getByName(String accountName) {
        Optional<Account> account = accountRepository.findById(accountName);
        return account.map(value -> mapper.map(value, AccountDto.class)).orElse(null);
    }
}
