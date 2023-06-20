package com.example.atmws.service;

import com.example.atmws.dto.OperationType;
import com.example.atmws.exception.types.AccountNotFoundException;
import com.example.atmws.exception.types.NotFundsException;
import com.example.atmws.model.Account;
import com.example.atmws.model.AccountTx;
import com.example.atmws.payload.TransferRequest;
import com.example.atmws.payload.TxRequest;
import com.example.atmws.payload.TxResponse;
import com.example.atmws.repository.AccountRepository;
import com.example.atmws.repository.AccountTxRepository;
import com.example.atmws.utils.NumberUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AtmOperationsServiceImpl implements AtmOperationsService {

    private final AccountRepository accountRepository;
    private final AccountTxRepository accountTxRepository;

    @Autowired
    public AtmOperationsServiceImpl(AccountRepository accountRepository, AccountTxRepository accountTxRepository) {
        this.accountRepository = accountRepository;
        this.accountTxRepository = accountTxRepository;
    }


    @Override
    @Transactional
    public TxResponse deposit(TxRequest txRequest) {
        Account account = getAccount(txRequest.getAccountName());
        account.setBalance(account.getBalance() + txRequest.getAmount());
        accountRepository.save(account);
        saveAccountTx(OperationType.DEPOSITO, txRequest.getAmount(), account);

        return buildTxResponse(account);
    }

    @Override
    @Transactional
    public TxResponse withdrawal(TxRequest txRequest) {
        Account account = getAccount(txRequest.getAccountName());

        if(NumberUtils.isNegativeSubtraction(account.getBalance(), txRequest.getAmount())) {
            throw new NotFundsException("Insufficient funds");
        } else {
            account.setBalance(account.getBalance() - txRequest.getAmount());
            accountRepository.save(account);
            saveAccountTx(OperationType.RETIRO, txRequest.getAmount(), account);

            return buildTxResponse(account);
        }
    }

    @Override
    @Transactional
    public TxResponse transfer(TransferRequest transferRequest) {
        Account accountSource = getAccount(transferRequest.getAccountNameSource());
        Account accountDestination = getAccount(transferRequest.getAccountNameDestination());

        if(NumberUtils.isNegativeSubtraction(accountSource.getBalance(), transferRequest.getAmount())) {
            throw new NotFundsException("Insufficient funds in source account");
        } else {
            accountSource.setBalance(accountSource.getBalance() - transferRequest.getAmount());
            accountRepository.save(accountSource);
            saveAccountTx(OperationType.TRANSFERENCIA, transferRequest.getAmount(), accountSource);

            accountDestination.setBalance(accountDestination.getBalance() + transferRequest.getAmount());
            accountRepository.save(accountDestination);
            saveAccountTx(OperationType.TRANSFERENCIA, transferRequest.getAmount(), accountDestination);
        }

        return buildTxResponse(accountSource);
    }

    private Account getAccount(String accountName){
        Optional<Account> accountOptional = accountRepository
                .findById(accountName);

        if(accountOptional.isEmpty()) {
            throw new AccountNotFoundException(
                    "Account not found",
                    List.of(String.format("With name: '%s'", accountName)));
        }

        return accountOptional.get();
    }

    private void saveAccountTx(OperationType operationType, Double amount, Account account){
        AccountTx accountTx = AccountTx.builder()
                .operationType(operationType.toString())
                .amount(amount)
                .account(account)
                .build();
        accountTxRepository.save(accountTx);
    }

    private TxResponse buildTxResponse(Account account){
        return TxResponse.builder()
                .accountName(account.getAccountName())
                .balance(account.getBalance())
                .updateDate(account.getUpdateDate())
                .build();
    }
}
