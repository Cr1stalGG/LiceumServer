package by.grsu.service.api;

import by.grsu.dto.account.AccountDto;
import by.grsu.dto.bonus.*;
import by.grsu.dto.bonus.bought_bonus.CheckBoughtBonusRequest;
import by.grsu.dto.bonus.bought_bonus.CheckBoughtBonusResponse;
import by.grsu.dto.transaction.TransactionDto;
import by.grsu.dto.transaction.TransactionResponse;
import by.grsu.edu.banking.utils.exception.TransactionException;

import java.util.List;

public interface AdminService {
    List<AccountDto> getAllAccountsGroupedByAmount();
    BonusCreationResponse createBonus(BonusCreationDto bonusDTO);
    TransactionResponse addAmountToCard(TransactionDto request);
    TransactionResponse getAmountFromCard(TransactionDto request) throws TransactionException;
    CheckBoughtBonusResponse checkBonusToken(CheckBoughtBonusRequest request);
    DeleteBonusResponse deleteBonus(String bonusName);
    UpdateBonusResponse updateBonus(long id, UpdateBonusRequest request);
}
