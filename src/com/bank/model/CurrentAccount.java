package com.bank.model;

import com.bank.exception.BankingException;


public class CurrentAccount extends Account {

    private double overdraftLimit;

    public CurrentAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public synchronized void deposit(double amount) {
        if (amount <= 0) {
            throw new BankingException("Invalid deposit amount.");
        }
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount));
    }

    @Override
    public synchronized void withdraw(double amount) {
        if (amount <= 0) {
            throw new BankingException("Invalid withdraw amount.");
        }

        if (balance + overdraftLimit < amount) {
            throw new BankingException("overdraft limit exceeded.");
        }

        balance -= amount;
        transactions.add(new Transaction("WITHDRAW", amount));
    }
}
