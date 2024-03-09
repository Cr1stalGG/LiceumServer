package by.grsu.service.api;

import by.grsu.dto.transaction.TransactionMainInfoDto;
import by.grsu.dto.transaction.TransactionRegistrationDto;

import java.util.List;

public interface TransactionService {
    void addTransaction(TransactionRegistrationDto transactionDTO);
    TransactionMainInfoDto getTransaction(long transactionId);
    List<TransactionMainInfoDto> getAllTransactions(String cardNumber);
}
