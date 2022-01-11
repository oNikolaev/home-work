package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.NonNull;

import java.io.IOException;


public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean isClientHasContract(@NonNull long clientId, @NonNull long contractNumber) throws IOException {
        return accountRepository.getAllAccountsByClientId(clientId).stream().anyMatch((contractNum) -> contractNum == contractNumber);
    }

    public boolean isContractCreated(@NonNull long contractId) {
        return accountRepository.getAccountById(contractId) != null;
    }
}
