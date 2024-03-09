package by.grsu.edu.banking.service.api;

import by.grsu.edu.banking.dto.account.AccountDto;
import by.grsu.edu.banking.dto.bonus.*;
import by.grsu.edu.banking.dto.bonus.bought_bonus.CheckBoughtBonusRequest;
import by.grsu.edu.banking.dto.bonus.bought_bonus.CheckBoughtBonusResponse;
import by.grsu.edu.banking.dto.transaction.TransactionDto;
import by.grsu.edu.banking.dto.transaction.TransactionResponse;
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
