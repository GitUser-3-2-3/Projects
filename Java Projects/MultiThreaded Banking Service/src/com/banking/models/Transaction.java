package com.banking.models;

import com.banking.exceptions.InsufficientFundsException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Getter
public class Transaction {
    private final String transactionId;
    private final Account sourceAccount;
    private final Account targetAccount;
    private final double amount;
    private final LocalDateTime timestamp;
    private TransactionStatus status;

    public Transaction(
        String transactionId, Account sourceAccount, Account targetAccount, double amount
    ) {
        this.transactionId = requireNonNull(transactionId);
        this.sourceAccount = requireNonNull(sourceAccount);
        this.targetAccount = requireNonNull(targetAccount);
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction must be greater than zero");
        }
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.status = TransactionStatus.PENDING;
    }

    /**
     * Transfers the specified amount from one account to another and updates the Status.
     *
     * @throws InsufficientFundsException If the source amount is insufficient.
     */
    public void execute() throws InsufficientFundsException {
        try {
            sourceAccount.transferTo(targetAccount, amount);
            updateStatus(TransactionStatus.SUCCESS);
        } catch (InsufficientFundsException iException) {
            updateStatus(TransactionStatus.FAILED);
            throw iException;
        }
    }

    /**
     * Updates the status of the transaction.
     *
     * @param newStatus The new status of the transaction.
     */
    private void updateStatus(TransactionStatus newStatus) {
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "transactionId='" + transactionId + '\'' +
            ", sourceAccount=" + sourceAccount +
            ", targetAccount=" + targetAccount +
            ", amount=" + amount +
            ", timestamp=" + timestamp +
            ", status=" + status +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;
        return Double.compare(amount, that.amount) == 0
            && Objects.equals(transactionId, that.transactionId)
            && Objects.equals(sourceAccount, that.sourceAccount)
            && Objects.equals(targetAccount, that.targetAccount)
            && Objects.equals(timestamp, that.timestamp)
            && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            transactionId, sourceAccount, targetAccount, amount, timestamp, status
        );
    }
}









