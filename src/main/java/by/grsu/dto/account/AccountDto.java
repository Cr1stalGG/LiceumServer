package by.grsu.dto.account;

import by.grsu.dto.bonus.bought_bonus.BoughtBonusDto;
import by.grsu.dto.card.CardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String username;
    private CardDto card;
    private List<BoughtBonusDto> bonuses;
}
