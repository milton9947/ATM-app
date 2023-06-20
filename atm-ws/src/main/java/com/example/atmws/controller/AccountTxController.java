package com.example.atmws.controller;

import com.example.atmws.dto.AccountTxDto;
import com.example.atmws.service.AccountTxService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atm-ws/tx/v1")
@Tag(name = "Transactions history")
public class AccountTxController {
    private final AccountTxService accountTxService;

    @Autowired
    public AccountTxController(AccountTxService accountTxService) {
        this.accountTxService = accountTxService;
    }

    @GetMapping
    public ResponseEntity<List<AccountTxDto>> findAll(){
        return new ResponseEntity<>(accountTxService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{accountName}")
    public ResponseEntity<List<AccountTxDto>> findAllByAccountName(@PathVariable String accountName){
        return new ResponseEntity<>(accountTxService.findAllByAccountName(accountName), HttpStatus.OK);
    }
}
