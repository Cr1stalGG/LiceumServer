package by.grsu.edu.banking.dto.convertor;

import by.grsu.edu.banking.dto.card.CardDto;
import by.grsu.edu.banking.entity.Card;
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
