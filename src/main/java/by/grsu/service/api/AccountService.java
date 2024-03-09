package by.grsu.service.api;

import by.grsu.dto.account.AccountDto;
import by.grsu.dto.auth.AuthenticationRequest;
import by.grsu.dto.auth.AuthenticationResponse;
import by.grsu.dto.bonus.BuyBonusRequest;
import by.grsu.dto.bonus.BuyBonusResponse;
import by.grsu.dto.bonus.RefuseBonusRequest;
import by.grsu.dto.bonus.RefuseBonusResponse;
import by.grsu.dto.card.CardDto;
import by.grsu.edu.banking.utils.exception.CardCreationException;

public interface AccountService {
    AccountDto getMainInfo(String username);

    CardDto getCard(String username) throws CardCreationException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    BuyBonusResponse buyBonus(BuyBonusRequest buyBonusRequest);

    RefuseBonusResponse refuseBonus(RefuseBonusRequest request);
}
