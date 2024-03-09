package by.grsu.dto.bonus.bought_bonus;

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
public class BoughtBonusDto {
    private String name;
    private String description;
    private String token;
}
