package com.example.atmws.repository;

import com.example.atmws.model.AccountTx;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountTxRepository extends CrudRepository<AccountTx, Long> {
    @Query("FROM AccountTx a WHERE a.account.accountName = :accountName")
    List<AccountTx> findAllByAccountName(@Param("accountName") String accountName);
}
