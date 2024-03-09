package by.grsu.service;

import by.grsu.dto.account.AccountDto;
import by.grsu.dto.bonus.*;
import by.grsu.dto.bonus.bought_bonus.CheckBoughtBonusRequest;
import by.grsu.dto.bonus.bought_bonus.CheckBoughtBonusResponse;
import by.grsu.dto.convertor.AccountDtoConvertor;
import by.grsu.dto.convertor.BonusDtoConvertor;
import by.grsu.dto.transaction.TransactionDto;
import by.grsu.dto.transaction.TransactionRegistrationDto;
import by.grsu.dto.transaction.TransactionResponse;
import by.grsu.entity.Account;
import by.grsu.entity.Bonus;
import by.grsu.entity.BoughtBonus;
import by.grsu.entity.Card;
import by.grsu.repository.AccountRepository;
import by.grsu.repository.BonusRepository;
import by.grsu.repository.BoughtBonusRepository;
import by.grsu.repository.CardRepository;
import by.grsu.service.api.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AccountRepository accountRepository;

    private final BoughtBonusRepository boughtBonusRepository;
    private final BonusRepository bonusRepository;

    private final CardRepository cardRepository;

    private final TransactionServiceImpl transactionService;

    private final String rootCardNumber = "GRSU";

    @Override
    public List<AccountDto> getAllAccountsGroupedByAmount() {
        List<AccountDto> accountsList = new ArrayList<>(accountRepository.findAll().stream().map(AccountDtoConvertor::convertEntityToDto).toList());

        AccountDto tmp;

        for(int i = 0; i < accountsList.size(); ++i){ //TODO optimize
            for(int j = 1; j < accountsList.size()-1; ++j)
                if(accountsList.get(j).getCard().getAmount() < accountsList.get(j+1).getCard().getAmount()){
                    tmp = accountsList.get(j-1);
                    accountsList.set(j-1, accountsList.get(j));
                    accountsList.set(j, tmp);
                }
        }

        return accountsList;
    }

    @Override
    public BonusCreationResponse createBonus(BonusCreationDto bonusDTO) {
        if (bonusRepository.existsByName(bonusDTO.getName()))
            return BonusCreationResponse.builder().state("false *bonus with name \"" + bonusDTO.getName() + "\" is already exist*").build();
        else {
            if(checkBonusCreation(bonusDTO)){
                bonusRepository.save(BonusDtoConvertor.convertDtoToEntity(bonusDTO));

                return BonusCreationResponse.builder().state("true").build();
             }
            else return BonusCreationResponse.builder().state("false *bad request data*").build();
        }
    }

    private boolean checkBonusCreation(BonusCreationDto bonusCreationDto){
        return !bonusCreationDto.getDescription().isEmpty() &&
                bonusCreationDto.getAmount() > 0 &&
                bonusCreationDto.getPrice() > 0 &&
               !bonusCreationDto.getName().isEmpty();
    }

    @Override
    public TransactionResponse addAmountToCard(TransactionDto request) {
        Card card = cardRepository.findByCardNumber(request.getCardNumber());

        card.addAmount(request.getAmount());
        cardRepository.save(card);

        transactionService.addTransaction(TransactionRegistrationDto.builder().fromCard(rootCardNumber).toCard(request.getCardNumber()).amount(request.getAmount()).description(request.getDescription()).build());

        return TransactionResponse.builder().state("true").build();
    }

    @Override
    @Transactional
    public TransactionResponse getAmountFromCard(TransactionDto request) {
        Card card = cardRepository.findByCardNumber(request.getCardNumber());

        if(card.getAmount() >= request.getAmount()) {
            card.getAmountROOT(request.getAmount());
            cardRepository.save(card);

            transactionService.addTransaction(TransactionRegistrationDto.builder().fromCard(request.getCardNumber()).toCard(rootCardNumber).amount(request.getAmount()).description(request.getDescription()).build());
            return TransactionResponse.builder().state("true").build();
        } else
            return TransactionResponse.builder().state("false *not enough money*").build();
    }

    @Override
    @Transactional
    public CheckBoughtBonusResponse checkBonusToken(CheckBoughtBonusRequest request) {
        CheckBoughtBonusResponse response;

        if(!boughtBonusRepository.existsByToken(request.getToken()))
            response = CheckBoughtBonusResponse.builder().state("false *no such token*").build();
        else {
            Account account = accountRepository.findByUsername(request.getUsername());
            BoughtBonus boughtBonus = boughtBonusRepository.findByToken(request.getToken());

            account.getBoughtBonuses().remove(boughtBonus);
            accountRepository.save(account);

            boughtBonusRepository.delete(boughtBonus);

            response = CheckBoughtBonusResponse.builder().state("true").build();
        }
        return response;
    }

    @Override
    public DeleteBonusResponse deleteBonus(String bonusName) {
        if(bonusRepository.existsByName(bonusName)){
            bonusRepository.delete(bonusRepository.findByName(bonusName));

            return DeleteBonusResponse.builder().state("true").build();
        }
        return DeleteBonusResponse.builder().state(String.format("false *no bonus with name %s*", bonusName)).build();
    }

    @Override
    public UpdateBonusResponse updateBonus(long id, UpdateBonusRequest request) {
        Bonus bonus = bonusRepository.findById(id);

        if(!request.getName().isEmpty())
            bonus.setName(request.getName());
        if(request.getPrice() > 0)
            bonus.setPrice(request.getPrice());
        if(!request.getDescription().isEmpty())
            bonus.setDescription(request.getDescription());
        if(request.getAmount() > 0)
            bonus.setAmount(request.getAmount());

        bonusRepository.save(bonus);

        return UpdateBonusResponse.builder().state("true").build();
    }
}
