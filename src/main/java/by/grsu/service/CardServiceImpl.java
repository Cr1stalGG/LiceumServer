package by.grsu.edu.banking.service;

import by.grsu.edu.banking.dto.transaction.TransactionRegistrationDto;
import by.grsu.edu.banking.entity.Account;
import by.grsu.edu.banking.entity.Card;
import by.grsu.edu.banking.repository.AccountRepository;
import by.grsu.edu.banking.repository.CardRepository;
import by.grsu.edu.banking.service.api.CardService;
import by.grsu.edu.banking.utils.exception.CardCreationException;
import by.grsu.edu.banking.utils.Generator;
import by.grsu.edu.banking.utils.exception.TransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final TransactionServiceImpl transactionService;

    @Override
    public void addCard(String username) throws CardCreationException {
        Account account = accountRepository.findByUsername(username);

        if(account.getCard() != null)
            throw new CardCreationException("Card is exist");

        Generator generator = new Generator();

        Card card = new Card();

        card.setCardNumber(generator.generateCardNumber());
        card.setAmount(0);

        account.setCard(card);
        cardRepository.save(card);
        accountRepository.save(account);
    }

    @Override
    public void transaction(TransactionRegistrationDto transactionDTO) throws TransactionException {
        if(!cardRepository.existsByCardNumber(transactionDTO.getToCard()))
            throw new TransactionException("card is not exist");

        Card card = cardRepository.findByCardNumber(transactionDTO.getFromCard());
        Card toCard = cardRepository.findByCardNumber(transactionDTO.getToCard());

        card.getAmount(transactionDTO.getAmount());
        toCard.addAmount(transactionDTO.getAmount());

        cardRepository.save(card);
        cardRepository.save(toCard);

        transactionService.addTransaction(transactionDTO);
    }

}
