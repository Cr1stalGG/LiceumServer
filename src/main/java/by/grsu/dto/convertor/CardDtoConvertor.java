package by.grsu.dto.convertor;

import by.grsu.dto.card.CardDto;
import by.grsu.entity.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardDtoConvertor {
    public static CardDto convertEntityToDto(Card source){
        return CardDto.builder()
                .cardNumber(source.getCardNumber())
                .amount(source.getAmount())
                .build();
    }
}
