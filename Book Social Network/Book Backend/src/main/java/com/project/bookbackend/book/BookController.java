package com.project.bookbackend.book;

import com.project.bookbackend.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Book")
@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Integer> saveBook(
        @Valid @RequestBody BookRequest req, Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.saveBook(req, connectedUser));
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<BookResponse> findBookById(
        @PathVariable("book-id") Integer bookId
    ) {
        return ResponseEntity.ok(bookService.findBookById(bookId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
        @RequestParam(name = "size", defaultValue = "10", required = false) int size,
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllBooks(page, size, connectedUser));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooksByOwner(
        @RequestParam(name = "size", defaultValue = "10", required = false) int size,
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllBooksByOwner(page, size, connectedUser));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllBorrowedBooks(
        @RequestParam(name = "size", defaultValue = "10", required = false) int size,
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllBorrowedBooks(page, size, connectedUser));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllReturnedBooks(
        @RequestParam(name = "size", defaultValue = "10", required = false) int size,
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllReturnedBooks(page, size, connectedUser));
    }

    @PatchMapping("/shareable/{book-id}")
    public ResponseEntity<Integer> updateShareableStatus(
        @PathVariable("book-id") Integer bookId, Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.updateShareableStatus(bookId, connectedUser));
    }

    @PatchMapping("/archived/{book-id}")
    public ResponseEntity<Integer> updateArchivedStatus(
        @PathVariable("book-id") Integer bookId, Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.updateArchivedStatus(bookId, connectedUser));
    }

    @PatchMapping("/borrow/{book-id}")
    public ResponseEntity<Integer> borrowBook(
        @PathVariable("book-id") Integer bookId, Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.borrowBook(bookId, connectedUser));
    }


    @PatchMapping("/borrow/{book-id}")
    public ResponseEntity<Integer> returnBorrowedBook(
        @PathVariable("book-id") Integer bookId, Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.returnBorrowedBook(bookId, connectedUser));
    }

    @PatchMapping("/borrow/{book-id}")
    public ResponseEntity<Integer> approveBookReturn(
        @PathVariable("book-id") Integer bookId, Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.approveBookReturn(bookId, connectedUser));
    }
}








