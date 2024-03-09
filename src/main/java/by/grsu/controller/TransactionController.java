package by.grsu.controller;

import by.grsu.dto.transaction.TransactionMainInfoDto;
import by.grsu.dto.transaction.TransactionRegistrationDto;
import by.grsu.edu.banking.utils.exception.TransactionException;
import by.grsu.service.CardServiceImpl;
import by.grsu.service.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionServiceImpl transactionService;
    private final CardServiceImpl cardService;

    @GetMapping("/{cardNumber}")
    public List<TransactionMainInfoDto> getAllTransactions(@PathVariable String cardNumber){
        return transactionService.getAllTransactions(cardNumber);
    }

    @PostMapping("/transaction")
    public String transaction(@RequestBody TransactionRegistrationDto transactionDTO){
        try {
            cardService.transaction(transactionDTO);

            return "true";
        } catch (TransactionException e) {
            return "false *not enough money exception*";
        }
    }
}
