package com.example.atmws.service;

import com.example.atmws.payload.TransferRequest;
import com.example.atmws.payload.TxRequest;
import com.example.atmws.payload.TxResponse;

public interface AtmOperationsService {
    TxResponse deposit(TxRequest txRequest);
    TxResponse withdrawal(TxRequest txRequest);
    TxResponse transfer(TransferRequest transferRequest);
}
