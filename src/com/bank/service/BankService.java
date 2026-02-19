package com.bank.service;

import com.bank.exception.BankingException;
import com.bank.model.Account;

import java.util.HashMap;
import java.util.Map;

public class BankService {
    private Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new BankingException("Account not found.");
        }
        return account;
    }

    public void transfer(String fromAcc, String toAcc, double amount) {

        Account source = getAccount(fromAcc);
        Account target = getAccount(toAcc);

        synchronized (this) {
            source.withdraw(amount);
            target.deposit(amount);
        }
    }

    public Map<String, Account> getAllAccounts() {
        return accounts;
    }
}
