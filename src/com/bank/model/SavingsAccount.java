package com.bank.model;

import com.bank.exception.BankingException;

public class SavingsAccount extends Account {


    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }


    @Override
    public synchronized void deposit(double amount) {

        if (amount <= 0) {
            throw new BankingException("Invalid deposit amount");
        }

        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount));
    }

    @Override
    public synchronized void withdraw(double amount) {
        if (amount <= 0) {
            throw new BankingException("Invalid withdraw amount");
        }

        if (amount < balance) {
            throw new BankingException("Insufficient balance");
        }

        balance -= amount;
        transactions.add(new Transaction("WITHDRAW", amount));
    }
}
