package by.grsu.edu.banking.service.api;

import by.grsu.edu.banking.dto.bonus.BonusDto;

import java.util.List;

public interface BonusService {
    List<BonusDto> getAllBonuses();
    BonusDto getBonusByName(String name);

    List<BonusDto> findBonusesByName(String name);
}
