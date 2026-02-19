package com.bank;

import com.bank.exception.BankingException;
import com.bank.model.Account;
import com.bank.model.CurrentAccount;
import com.bank.model.SavingsAccount;
import com.bank.repository.FileRepository;
import com.bank.service.BankService;

import java.util.Map;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {

        BankService service = new BankService();

        // Create Accounts
        Account saving = new SavingsAccount("111", 1000);
        Account current = new CurrentAccount("222", 500, 300);

        service.addAccount(saving);
        service.addAccount(current);

        // Basic Operations
        saving.deposit(200);
        current.withdraw(100);

        // Transfer
        service.transfer("111", "222", 70);

        // Thread-Safety Test
        Thread t1 = new Thread(
                () -> {
                    service.transfer("111", "222", 50);
                }
        );

        Thread t2 = new Thread(
                () -> {
                    service.transfer("111", "222", 70);
                }
        );

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Exception Test
        try {
            saving.withdraw(100000);
        } catch (BankingException e) {
            out.println("exception caught: " + e.getMessage());
        }

        // Print Balances
        out.println("Saving Balance: " + saving.getBalance());
        out.println("Current Balance: " + current.getBalance());

        // Print Transaction History
        out.println("\nSaving Transactions:");
        saving.getTransactions().forEach(out::println);


        out.println("\nCurrent Transaction:");
        current.getTransactions().forEach(out::println);

        // save
        FileRepository.save(service.getAllAccounts());

        // load
        Map<String, Account> load = FileRepository.load();

        if (load != null) {
            out.println("\nloaded from file");
            load.values().forEach(acc ->
                    out.println(acc.getAccountNumber() + " -> " + acc.getBalance()));
        }
    }
}
