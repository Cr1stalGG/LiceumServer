package by.grsu.controller;

import by.grsu.dto.account.AccountDto;
import by.grsu.dto.bonus.*;
import by.grsu.dto.bonus.bought_bonus.CheckBoughtBonusRequest;
import by.grsu.dto.bonus.bought_bonus.CheckBoughtBonusResponse;
import by.grsu.dto.transaction.TransactionDto;
import by.grsu.dto.transaction.TransactionResponse;
import by.grsu.service.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminServiceImpl adminService;

    @GetMapping("/get-accounts")
    public List<AccountDto> getAccountsGroupedByAmount(){
        return adminService.getAllAccountsGroupedByAmount();
    }

    @PostMapping("/create-bonus")
    public BonusCreationResponse createBonus(@RequestBody BonusCreationDto bonusDTO){
        return adminService.createBonus(bonusDTO);
    }

    @PostMapping("/add")
    public TransactionResponse addAmount(@RequestBody TransactionDto transactionDTO){
        return adminService.addAmountToCard(transactionDTO);
    }

    @PostMapping("/get")
    public TransactionResponse getAmount(@RequestBody TransactionDto transactionDTO){
        return adminService.getAmountFromCard(transactionDTO);
    }

    @PostMapping("/check")
    public CheckBoughtBonusResponse checkBoughtBonus(@RequestBody CheckBoughtBonusRequest request){
        return adminService.checkBonusToken(request);
    }

    @DeleteMapping("/{bonusName}")
    public DeleteBonusResponse deleteBonus(@PathVariable String bonusName){
        return adminService.deleteBonus(bonusName);
    }

    @PutMapping("/{id}")
    public UpdateBonusResponse updateBonud(@PathVariable long id, @RequestBody UpdateBonusRequest request){
        return adminService.updateBonus(id, request);
    }
}
