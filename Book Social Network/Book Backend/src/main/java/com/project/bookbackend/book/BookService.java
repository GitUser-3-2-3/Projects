package com.project.bookbackend.book;

import com.project.bookbackend.common.PageResponse;
import com.project.bookbackend.records.BookTransactionHistory;
import com.project.bookbackend.repo.BookRepository;
import com.project.bookbackend.repo.TransactionHistoryRepository;
import com.project.bookbackend.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.bookbackend.book.BookSpecification.withOwnerId;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final TransactionHistoryRepository transactionHistoryRepository;

    public Integer saveBook(BookRequest req, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Book book = bookMapper.toBook(req);
        book.setOwner(user);
        return bookRepository.save(book).getId();
    }

    public BookResponse findBookById(final Integer bookId) {
        return bookRepository.findById(bookId)
            .map(bookMapper::toBookResponse)
            .orElseThrow(() -> new EntityNotFoundException("No Book found with Id::" + bookId));
    }


    public PageResponse<BookResponse> findAllBooks(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAllDisplayableBooks(pageable, user.getId());

        List<BookResponse> bookResponseList = books.stream()
            .map(bookMapper::toBookResponse)
            .toList();

        return new PageResponse<>(
            bookResponseList, books.getNumber(), books.getNumber(),
            books.getTotalElements(), books.getTotalPages(),
            books.isFirst(), books.isLast()
        );
    }

    public PageResponse<BookResponse> findAllBooksByOwner(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAll(withOwnerId(user.getId()), pageable);

        List<BookResponse> bookResponseList = books.stream()
            .map(bookMapper::toBookResponse)
            .toList();

        return new PageResponse<>(
            bookResponseList, books.getNumber(), books.getNumber(),
            books.getTotalElements(), books.getTotalPages(),
            books.isFirst(), books.isLast()
        );
    }

    public PageResponse<BorrowedBookResponse> findAllBorrowedBooks(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<BookTransactionHistory> borrowedBooks = transactionHistoryRepository
            .findAllBorrowedBooks(pageable, user.getId());

        List<BorrowedBookResponse> bookResponseList = borrowedBooks.stream()
            .map(bookMapper::toBorrowedBookResponse)
            .toList();

        return new PageResponse<>(
            bookResponseList, borrowedBooks.getNumber(), borrowedBooks.getNumber(),
            borrowedBooks.getTotalElements(), borrowedBooks.getTotalPages(),
            borrowedBooks.isFirst(), borrowedBooks.isLast()
        );
    }
}







