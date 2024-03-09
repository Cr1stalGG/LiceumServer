package by.grsu.dto.bonus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateBonusRequest {
    private String name;
    private String description;
    private long price;
    private long amount;
}
