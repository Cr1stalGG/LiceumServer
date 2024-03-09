package by.grsu.edu.banking.dto.bonus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BonusCreationDto {
    private String name;
    private String description;
    private long price;
    private long amount;
}
