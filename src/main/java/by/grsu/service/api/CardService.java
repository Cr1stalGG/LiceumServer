package by.grsu.service.api;

import by.grsu.dto.transaction.TransactionRegistrationDto;
import by.grsu.edu.banking.utils.exception.CardCreationException;
import by.grsu.edu.banking.utils.exception.TransactionException;

public interface CardService {
    void addCard(String username) throws CardCreationException;

    void transaction(TransactionRegistrationDto transactionDTO) throws TransactionException;

}
