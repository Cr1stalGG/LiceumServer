package by.grsu.edu.banking.dto.convertor;

import by.grsu.edu.banking.entity.Transaction;
import by.grsu.edu.banking.dto.transaction.TransactionMainInfoDto;
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
