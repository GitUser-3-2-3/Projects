package com.banking.models;

import com.banking.exceptions.InsufficientFundsException;
import lombok.Getter;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.Objects.requireNonNull;

@Getter
public class Account {
    private final String accountNumber;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = requireNonNull(accountNumber);
        this.balance = initialBalance;
    }

    /**
     * Deposits a specified amount to this account.
     *
     * @param amount The amount to be deposited.
     * @throws IllegalArgumentException If amount is negative.
     */
    public void deposit(final double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Withdraws a specified amount from this account.
     *
     * @param amount The amount to be withdrawn
     * @throws IllegalArgumentException   If amount is negative.
     * @throws InsufficientFundsException If balance is less than amount.
     */
    public void withdraw(final double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }

        lock.lock();
        try {
            if (balance < amount) {
                throw new InsufficientFundsException("Insufficient funds for withdrawal");
            }
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Transfers a specified amount to another account.
     *
     * @param targetAccount The account to transfer to.
     * @param amount        The amount to transfer.
     * @throws IllegalArgumentException   If amount is zero or negative.
     * @throws InsufficientFundsException If balance is less than amount.
     */
    public void transferTo(Account targetAccount, final double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than 0");
        }

        // Implement deadlock avoidance by locking accounts in a consistent order based on account number
        Account firstLock = this.accountNumber.compareTo(targetAccount.accountNumber) < 0 ? this : targetAccount;
        Account secondLock = this == firstLock ? targetAccount : this;

        firstLock.lock.lock();
        try {
            secondLock.lock.lock();
            try {
                if (this.balance < amount) {
                    throw new InsufficientFundsException("Insufficient funds for transfer");
                }
                this.withdraw(amount);
                targetAccount.deposit(amount);
            } finally {
                secondLock.lock.unlock();
            }
        } finally {
            firstLock.lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Account{" +
            "accountNumber='" + accountNumber + '\'' +
            ", balance=" + balance +
            ", lock=" + lock +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(balance, account.balance) == 0
            && Objects.equals(accountNumber, account.accountNumber)
            && Objects.equals(lock, account.lock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, lock);
    }
}









