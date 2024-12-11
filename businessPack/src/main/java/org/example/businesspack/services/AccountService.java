package org.example.businesspack.services;

import org.example.businesspack.dto.AccountDto;

import java.util.List;

public interface AccountService {

    Long saveAccount(AccountDto account);
    List<AccountDto> getAccounts();
    void deleteAccount(AccountDto account);
    Long updateAccount(AccountDto currentAccount, AccountDto updateAccount);

}
