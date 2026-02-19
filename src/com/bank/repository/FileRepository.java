package com.bank.repository;

import com.bank.model.Account;

import java.io.*;
import java.util.Map;

public class FileRepository {

    private static final String FILE_NAME = "account.dat";

    public static void save(Map<String, Account> accounts) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Account> load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Map<String, Account>) ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}
