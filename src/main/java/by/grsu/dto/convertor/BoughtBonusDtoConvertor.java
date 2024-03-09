package by.grsu.edu.banking.dto.convertor;

import by.grsu.edu.banking.entity.BoughtBonus;
import by.grsu.edu.banking.dto.bonus.bought_bonus.BoughtBonusDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BoughtBonusDtoConvertor {
    public static BoughtBonusDto convertEntityToDto(BoughtBonus source){
        return BoughtBonusDto.builder()
                .name(source.getName())
                .description(source.getDescription())
                .token(source.getToken())
                .build();
    }
}
