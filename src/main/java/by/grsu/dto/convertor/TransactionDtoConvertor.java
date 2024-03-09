package by.grsu.dto.convertor;

import by.grsu.dto.transaction.TransactionMainInfoDto;
import by.grsu.entity.Transaction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransactionDtoConvertor {
    public static TransactionMainInfoDto convertEntityToDto(Transaction source){
        return TransactionMainInfoDto.builder()
                .transactionID(source.getId())
                .amount(source.getAmount())
                .fromCard(source.getFromCard())
                .toCard(source.getToCard())
                .description(source.getDescription())
                .date(source.getDate())
                .build();
    }
}
