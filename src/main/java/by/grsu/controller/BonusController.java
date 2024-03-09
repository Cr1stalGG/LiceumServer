package by.grsu.controller;

import by.grsu.dto.bonus.BonusDto;
import by.grsu.service.BonusServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bonuses")
@RequiredArgsConstructor
public class BonusController {
    private final BonusServiceImpl bonusService;

    @GetMapping("/all")
    public List<BonusDto> getAll(){
        return bonusService.getAllBonuses();
    }

    @GetMapping("/search/{name}")
    public List<BonusDto> searchBonus(@PathVariable String name){
        return bonusService.findBonusesByName(name);
    }

    @GetMapping("/{name}")
    public BonusDto getBonusByName(@PathVariable String name){
        return bonusService.getBonusByName(name);
    }
}
