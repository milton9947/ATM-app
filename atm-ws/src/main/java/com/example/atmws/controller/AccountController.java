package com.example.atmws.controller;

import com.example.atmws.dto.AccountDto;
import com.example.atmws.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/atm-ws/account/v1")
@CrossOrigin
@Tag(name = "Account Operations")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> findAll(){
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/{accountName}")
    public ResponseEntity<AccountDto> getByName(@PathVariable String accountName){
        AccountDto accountDto = accountService.getByName(accountName);

        return Objects.isNull(accountDto) ?
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND):
                new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
}
