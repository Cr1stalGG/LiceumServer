package by.grsu.service;

import by.grsu.dto.account.AccountDto;
import by.grsu.dto.auth.AuthenticationRequest;
import by.grsu.dto.auth.AuthenticationResponse;
import by.grsu.dto.bonus.BuyBonusRequest;
import by.grsu.dto.bonus.BuyBonusResponse;
import by.grsu.dto.bonus.RefuseBonusRequest;
import by.grsu.dto.bonus.RefuseBonusResponse;
import by.grsu.dto.card.CardDto;
import by.grsu.dto.convertor.AccountDtoConvertor;
import by.grsu.dto.convertor.CardDtoConvertor;
import by.grsu.edu.banking.utils.exception.BuyBonusException;
import by.grsu.edu.banking.utils.exception.CardCreationException;
import by.grsu.edu.banking.utils.exception.TransactionException;
import by.grsu.entity.Account;
import by.grsu.entity.Bonus;
import by.grsu.entity.BoughtBonus;
import by.grsu.entity.Card;
import by.grsu.repository.AccountRepository;
import by.grsu.repository.BonusRepository;
import by.grsu.repository.BoughtBonusRepository;
import by.grsu.security.config.AccountUserDetailsConfig;
import by.grsu.security.jwt.JwtService;
import by.grsu.service.api.AccountService;
import by.grsu.utils.Generator;
import by.grsu.utils.api.grsu.AuthException;
import by.grsu.utils.api.grsu.AuthStateParser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountDtoConvertor accountDtoConvertor;
    private final AccountRepository accountRepository;
    private final BonusRepository bonusRepository;
    private final BoughtBonusRepository boughtBonusRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AccountDto getMainInfo(String username) {
        return AccountDtoConvertor.convertEntityToDto(accountRepository.findByUsername(username.toLowerCase()));
    }

    @Override
    public CardDto getCard(String username) throws CardCreationException {
        Card card = accountRepository.findByUsername(username.toLowerCase()).getCard();

        if(card == null)
            throw new CardCreationException("Card is not created");

        return CardDtoConvertor.convertEntityToDto(card);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            if(accountRepository.existsByUsername(request.getUsername().toLowerCase())) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername().toLowerCase(), request.getPassword()));

                Account account = Optional.ofNullable(accountRepository.findByUsername(request.getUsername().toLowerCase())).orElseThrow();

                var jwtToken = jwtService.generateToken(new AccountUserDetailsConfig(account));

                return AuthenticationResponse.builder().token(jwtToken).role(account.getRole()).build();
            } else {
                AuthStateParser.getAuthState(request.getUsername().toLowerCase(), request.getPassword());

                Account account = accountDtoConvertor.convertDtoToEntity(request);
                accountRepository.save(account);

                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername().toLowerCase(), request.getPassword()));

                account = Optional.ofNullable(accountRepository.findByUsername(request.getUsername().toLowerCase())).orElseThrow();

                var jwtToken = jwtService.generateToken(new AccountUserDetailsConfig(account));

                return AuthenticationResponse.builder().token(jwtToken).role(account.getRole()).build();

            }

        } catch (AuthException e) {
            return new AuthenticationResponse("false *invalid credentials*", null);
        } catch (IOException e) {
            return new AuthenticationResponse("false *server error*", null);
        }
    }

    @Transactional
    @Override
    public BuyBonusResponse buyBonus(BuyBonusRequest buyBonusRequest) {
        Account account = accountRepository.findByUsername(buyBonusRequest.getUsername().toLowerCase());
        Bonus bonus = bonusRepository.findByName(buyBonusRequest.getBonusName());

        try {
            account.getCard().getAmount(bonus.getPrice());

            Generator generator = new Generator();
            String token = generator.generateBonusToken((byte) 7);

            BoughtBonus boughtBonus = BoughtBonus.builder()
                    .name(bonus.getName())
                    .description(bonus.getDescription())
                    .token(token)
                    .price(bonus.getPrice())
                    .build();

            if(!buyBonus(bonus))
                bonusRepository.deleteById(bonus.getId());
            else
                bonusRepository.save(bonus);

            bonus.setAmount(bonus.getAmount()-1);

            boughtBonusRepository.save(boughtBonus);

            account.getBoughtBonuses().add(boughtBonus);
             accountRepository.save(account);

            return BuyBonusResponse.builder().token(token).build();
        } catch (TransactionException e) {
            return BuyBonusResponse.builder().token("false *not enough money*").build();
        } catch (BuyBonusException e) {
            return BuyBonusResponse.builder().token("false *there are no bonuses*").build();
        }
    }

    private boolean buyBonus(Bonus bonus) throws BuyBonusException {
        if(bonus.getAmount() == 1)
            return false;

        if(bonus.getAmount() <= 0)
            throw new BuyBonusException("There are no bonuses here");

        return true;
    }

    @Override
    public RefuseBonusResponse refuseBonus(RefuseBonusRequest request) {
        Account account = accountRepository.findByUsername(request.getUsername());
        Bonus bonus;
        BoughtBonus boughtBonus = boughtBonusRepository.findByNameAndToken(request.getBonusName(), request.getToken());

        account.getBoughtBonuses().removeIf(x -> Objects.equals(x.getToken(), request.getToken()) && Objects.equals(x.getName(), request.getBonusName()));
        account.getCard().setAmount(account.getCard().getAmount() + boughtBonus.getPrice());
        accountRepository.save(account);

        if(bonusRepository.findByName(request.getBonusName()) != null) {
            bonus = bonusRepository.findByName(request.getBonusName());

            bonus.setAmount(bonus.getAmount() + 1);
            bonusRepository.save(bonus);
        } else {
            bonus = Bonus.builder()
                    .name(boughtBonus.getName())
                    .description(boughtBonus.getDescription())
                    .price(boughtBonus.getPrice())
                    .amount(1)
                    .build();

            bonusRepository.save(bonus);
        }

        boughtBonusRepository.delete(boughtBonus);

        return RefuseBonusResponse.builder().response("true").build();
    }

}
