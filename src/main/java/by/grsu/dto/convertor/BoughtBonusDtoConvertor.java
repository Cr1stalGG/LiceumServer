package by.grsu.dto.convertor;

import by.grsu.dto.bonus.bought_bonus.BoughtBonusDto;
import by.grsu.entity.BoughtBonus;
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
