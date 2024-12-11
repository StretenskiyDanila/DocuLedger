package org.example.businesspack.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.businesspack.dto.AccountDto;
import org.example.businesspack.entities.DataWork;
import org.example.businesspack.repositories.AccountRepository;
import org.example.businesspack.repositories.TableRepository;
import org.example.businesspack.services.AccountService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final TableRepository<DataWork> accountRepository = new AccountRepository();

    @Override
    public Long saveAccount(AccountDto account) {
        DataWork accountSave = AccountDto.to(account);
        Long id = null;
        try {
            id = accountRepository.save(accountSave);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return id;
    }

    @Override
    public List<AccountDto> getAccounts() {
        List<AccountDto> models = new ArrayList<>();
        try {
            List<DataWork> accounts = accountRepository.getAll();
            if (accounts != null) {
                models = accounts.stream()
                        .map(AccountDto::of)
                        .collect(Collectors.toList());
            } else {
                return models;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return models;
    }

    @Override
    public void deleteAccount(AccountDto account) {
        DataWork accountDelete = AccountDto.to(account);
        try {
            accountRepository.delete(accountDelete);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Long updateAccount(AccountDto currentAccount, AccountDto updateAccount) {
        DataWork accountUpdate = AccountDto.to(updateAccount);
        DataWork accountCurrent = AccountDto.to(currentAccount);
        Long id = null;
        try {
            id = accountRepository.update(accountUpdate, accountCurrent);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return id;
    }

}
