package by.grsu.edu.banking.dto.convertor;

import by.grsu.edu.banking.dto.bonus.BonusDto;
import by.grsu.edu.banking.entity.Bonus;
import by.grsu.edu.banking.dto.bonus.BonusCreationDto;
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
