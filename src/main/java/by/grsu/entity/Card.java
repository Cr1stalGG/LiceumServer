package by.grsu.edu.banking.entity;

import by.grsu.edu.banking.utils.exception.TransactionException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cardNumber;
    private long amount;

    public void addAmount(long amount){
        this.amount += amount;

    }

    public void getAmount(long amount) throws TransactionException {
        if(this.amount - amount > 0)
            this.amount -= amount;
        else
            throw new TransactionException("Not enought points");
    }

    public void getAmountROOT(long amount){
        this.amount -= amount;
    }
}
