package by.grsu.controller;

import by.grsu.dto.account.AccountDto;
import by.grsu.dto.bonus.BuyBonusRequest;
import by.grsu.dto.bonus.BuyBonusResponse;
import by.grsu.dto.bonus.RefuseBonusRequest;
import by.grsu.dto.bonus.RefuseBonusResponse;
import by.grsu.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountServiceImpl accountService;

    @GetMapping("/{username}")
    public AccountDto getMainInfo(@PathVariable String username){
        return accountService.getMainInfo(username.toLowerCase());
    }

    @PostMapping("/buy-bonus")
    public BuyBonusResponse buyBonus(@RequestBody BuyBonusRequest buyBonusRequest){
        return accountService.buyBonus(buyBonusRequest);
    }

    @PostMapping("/refuse-bonus")
    public RefuseBonusResponse refuseBonus(@RequestBody RefuseBonusRequest request){
        return accountService.refuseBonus(request);
    }

}
