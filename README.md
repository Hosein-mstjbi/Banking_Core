# 🏦 Banking Core System (Java)

A simple Core Banking System implemented in pure Java.

This project demonstrates core banking operations including account management, transactions, transfers, thread-safety, custom exception handling, and file-based persistence.

---

## 🚀 Features

- Abstract `Account` model
- `SavingsAccount` implementation
- `CurrentAccount` implementation
- Transaction history tracking
- Transfer between accounts
- Thread-safe operations
- Custom `BankingException`
- File-based persistence using Java Serialization
- Runnable application with Main entry point

---

## 🏗️ Project Structure

com.bank
│

├── model

│ ├── Account.java

│ ├── SavingsAccount.java

│ ├── CurrentAccount.java

│ └── Transaction.java

│

├── service

│ └── BankService.java

│

├── repository

│ └── FileRepository.java

│

├── exception

│ └── BankingException.java

│

└── Main.java



---

## 🧠 Architecture Overview

This project follows a simple layered architecture:

- **Model Layer** → Domain entities (Account, Transaction)
- **Service Layer** → Business logic (transfer, deposit, withdraw)
- **Repository Layer** → Persistence (file-based storage)
- **Exception Layer** → Custom domain-specific exceptions

---

## 🔒 Thread Safety

- Critical balance operations are synchronized.
- Transfer logic ensures safe withdrawal and deposit execution.
- Designed to prevent race conditions.

---

## 💾 Persistence

Data is stored using Java Object Serialization in a `.dat` file.

- `ObjectOutputStream` for saving
- `ObjectInputStream` for loading

All domain models implement `Serializable`.


