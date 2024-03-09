package by.grsu.dto.transaction;

import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionMainInfoDto {
    private long transactionID;
    private String fromCard;
    private String toCard;
    private long amount;
    private String description;
    private Date date;
}
