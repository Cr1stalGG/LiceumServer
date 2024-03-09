package by.grsu.dto.bonus.bought_bonus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckBoughtBonusRequest {
    private String username;
    private String token;
}
