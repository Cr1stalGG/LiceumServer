package by.grsu.edu.banking.dto.convertor;

import by.grsu.edu.banking.dto.account.AccountDto;
import by.grsu.edu.banking.dto.auth.AuthenticationRequest;
import by.grsu.edu.banking.entity.Account;
import by.grsu.edu.banking.entity.enumiration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class AccountDtoConvertor {
    private final PasswordEncoder passwordEncoder;

    public static AccountDto convertEntityToDto(Account source){
        return AccountDto.builder()
                .username(source.getUsername())
                .card(source.getCard() == null ? null : CardDtoConvertor.convertEntityToDto(source.getCard()))
                .bonuses(source.getBoughtBonuses().stream().map(BoughtBonusDtoConvertor::convertEntityToDto).toList())
                .build();
    }

    public Account convertDtoToEntity(AuthenticationRequest source){
        final String userLoginPattern = "^[a-zA-Z]*_[a-zA-Z]{2}_[0-9]{2}$";

        Account account = Account.builder()
                .username(source.getUsername().toLowerCase())
                .password(passwordEncoder.encode(source.getPassword()))
                .build();

        if(Pattern.matches(userLoginPattern, source.getUsername()))
            account.setRole(Role.USER);
        else
            account.setRole(Role.ADMIN);

        return account;
    }
}
