package by.grsu.controller;

import by.grsu.dto.card.CardDto;
import by.grsu.edu.banking.utils.exception.CardCreationException;
import by.grsu.service.AccountServiceImpl;
import by.grsu.service.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts/{username}/cards")
@RequiredArgsConstructor
public class CardController {
    private final AccountServiceImpl accountService;
    private final CardServiceImpl cardService;

    @GetMapping()
    public CardDto getCard(@PathVariable String username){
        try {
            return accountService.getCard(username);
        } catch (CardCreationException e) {
            return null;
        }
    }

    @PostMapping()
    public String addCard(@PathVariable String username){
        try {
            cardService.addCard(username);

            return "true";
        } catch (CardCreationException e) {
            return "false *card is already exist*";
        }
    }

}
