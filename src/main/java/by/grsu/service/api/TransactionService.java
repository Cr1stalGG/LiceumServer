package by.grsu.edu.banking.service.api;

import by.grsu.edu.banking.dto.transaction.TransactionMainInfoDto;
import by.grsu.edu.banking.dto.transaction.TransactionRegistrationDto;

import java.util.List;

public interface TransactionService {
    void addTransaction(TransactionRegistrationDto transactionDTO);
    TransactionMainInfoDto getTransaction(long transactionId);
    List<TransactionMainInfoDto> getAllTransactions(String cardNumber);
}
