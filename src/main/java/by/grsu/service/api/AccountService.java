package by.grsu.edu.banking.service.api;

import by.grsu.edu.banking.dto.account.AccountDto;
import by.grsu.edu.banking.dto.auth.AuthenticationRequest;
import by.grsu.edu.banking.dto.auth.AuthenticationResponse;
import by.grsu.edu.banking.dto.bonus.BuyBonusRequest;
import by.grsu.edu.banking.dto.bonus.BuyBonusResponse;
import by.grsu.edu.banking.dto.bonus.RefuseBonusRequest;
import by.grsu.edu.banking.dto.bonus.RefuseBonusResponse;
import by.grsu.edu.banking.dto.card.CardDto;
import by.grsu.edu.banking.utils.exception.CardCreationException;

public interface AccountService {
    AccountDto getMainInfo(String username);

    CardDto getCard(String username) throws CardCreationException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    BuyBonusResponse buyBonus(BuyBonusRequest buyBonusRequest);

    RefuseBonusResponse refuseBonus(RefuseBonusRequest request);
}
