package by.grsu.service;

import by.grsu.dto.bonus.BonusDto;
import by.grsu.dto.convertor.BonusDtoConvertor;
import by.grsu.repository.BonusRepository;
import by.grsu.service.api.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {
    private final BonusRepository bonusRepository;

    @Override
    public List<BonusDto> getAllBonuses() {
        return bonusRepository.findAll().stream().map(BonusDtoConvertor::convertEntityToDto).toList();
    }

    @Override
    public BonusDto getBonusByName(String name) {
        return BonusDtoConvertor.convertEntityToDto(bonusRepository.findByName(name));
    }

    @Override
    public List<BonusDto> findBonusesByName(String name) {
        return bonusRepository.findByNameLike(name).stream().map(BonusDtoConvertor::convertEntityToDto).toList();
    }
}
