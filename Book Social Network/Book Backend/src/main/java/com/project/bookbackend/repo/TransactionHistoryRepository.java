package com.project.bookbackend.repo;

import com.project.bookbackend.records.BookTransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {

    @Query("""
        select transaction from BookTransactionHistory transaction
        where transaction.user.id = :userId
        """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Integer userId);
}
