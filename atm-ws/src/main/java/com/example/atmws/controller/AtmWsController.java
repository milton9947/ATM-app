package com.example.atmws.controller;

import com.example.atmws.exception.types.InvalidDataException;
import com.example.atmws.payload.TransferRequest;
import com.example.atmws.payload.TxRequest;
import com.example.atmws.payload.TxResponse;
import com.example.atmws.service.AtmOperationsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atm-ws/atm/v1")
@CrossOrigin
@Tag(name = "ATM Operations")
public class AtmWsController {

    private final AtmOperationsService atmOperationsService;

    @Autowired
    public AtmWsController(AtmOperationsService atmOperationsService) {
        this.atmOperationsService = atmOperationsService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<TxResponse> deposit(@Valid @RequestBody TxRequest txRequest,
                                              BindingResult bindingResult){
        if( bindingResult.hasErrors() ) {
            throw  new InvalidDataException(bindingResult);
        }

        return new ResponseEntity<>(atmOperationsService.deposit(txRequest), HttpStatus.CREATED);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<TxResponse> withdrawal(@Valid @RequestBody TxRequest txRequest,
                                              BindingResult bindingResult){
        if( bindingResult.hasErrors() ) {
            throw  new InvalidDataException(bindingResult);
        }

        return new ResponseEntity<>(atmOperationsService.withdrawal(txRequest), HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TxResponse> transfer(@Valid @RequestBody TransferRequest transferRequest,
                                                 BindingResult bindingResult){
        if( bindingResult.hasErrors() ) {
            throw  new InvalidDataException(bindingResult);
        }

        return new ResponseEntity<>(atmOperationsService.transfer(transferRequest), HttpStatus.OK);
    }
}
