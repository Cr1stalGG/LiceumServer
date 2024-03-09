package by.grsu.service;

import by.grsu.dto.transaction.TransactionRegistrationDto;
import by.grsu.edu.banking.utils.exception.CardCreationException;
import by.grsu.edu.banking.utils.exception.TransactionException;
import by.grsu.entity.Account;
import by.grsu.entity.Card;
import by.grsu.repository.AccountRepository;
import by.grsu.repository.CardRepository;
import by.grsu.service.api.CardService;
import by.grsu.utils.Generator;
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
