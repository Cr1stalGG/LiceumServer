package by.grsu.dto.convertor;

import by.grsu.dto.bonus.BonusCreationDto;
import by.grsu.dto.bonus.BonusDto;
import by.grsu.entity.Bonus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BonusDtoConvertor {
    public static Bonus convertDtoToEntity(BonusCreationDto source){
        return Bonus.builder()
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .amount(source.getAmount())
                .build();
    }

    public static BonusDto convertEntityToDto(Bonus source){
        return BonusDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .amount(source.getAmount())
                .build();
    }
}
