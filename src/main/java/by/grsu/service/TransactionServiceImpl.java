package by.grsu.service;

import by.grsu.dto.convertor.TransactionDtoConvertor;
import by.grsu.dto.transaction.TransactionMainInfoDto;
import by.grsu.dto.transaction.TransactionRegistrationDto;
import by.grsu.entity.Transaction;
import by.grsu.repository.TransactionRepository;
import by.grsu.service.api.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public void addTransaction(TransactionRegistrationDto transactionDTO) {
        Transaction transaction = new Transaction();

        transaction.setFromCard(transactionDTO.getFromCard());
        transaction.setToCard(transactionDTO.getToCard());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setDate(Date.valueOf(LocalDate.now()));

        transactionRepository.save(transaction);
    }

    @Override
    public TransactionMainInfoDto getTransaction(long transactionId) {
        return TransactionDtoConvertor.convertEntityToDto(transactionRepository.findById(transactionId));
    }

    @Override
    public List<TransactionMainInfoDto> getAllTransactions(String cardNumber) {
        return transactionRepository.findAllByCardNumber(cardNumber).stream().map(TransactionDtoConvertor::convertEntityToDto).toList();
    }

}
