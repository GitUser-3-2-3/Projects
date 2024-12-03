import {Component, OnInit} from '@angular/core';
import {PageResponseBorrowedBookResponse} from "../../../../services/models/page-response-borrowed-book-response";
import {BookService} from "../../../../services/services/book.service";
import {BorrowedBookResponse} from "../../../../services/models/borrowed-book-response";

@Component({
    selector: 'app-return-books',
    templateUrl: './return-books.component.html',
    styleUrls: ['./return-books.component.scss']
})
export class ReturnBooksComponent implements OnInit {

    returnedBooks: PageResponseBorrowedBookResponse = {};
    level = 'success';
    message = '';
    page = 0;
    size = 10;

    constructor(private bookService: BookService) {
    }

    ngOnInit(): void {
        this.findAllReturnedBooks();
    }

    private findAllReturnedBooks() {
        this.bookService.findAllReturnedBooks({page: this.page, size: this.size}).subscribe({
            next: (res) => {
                this.returnedBooks = res;
            }
        })
    }

    goToFirstPage() {
        this.page = 0;
        this.findAllReturnedBooks();
    }

    goToPreviousPage() {
        this.page = this.page - 1;
        this.findAllReturnedBooks();
    }

    goToPage(index: number) {
        this.page = index;
        this.findAllReturnedBooks();
    }

    goToNextPage() {
        this.page = this.page + 1;
        this.findAllReturnedBooks();
    }

    goToLastPage() {
        this.page = this.returnedBooks.totalPages as number - 1;
        this.findAllReturnedBooks();
    }

    get isLastPage(): boolean {
        return this.page == this.returnedBooks.totalPages as number - 1;
    }

    approveReturn(book: BorrowedBookResponse) {
        if (!book.returned) {
            this.level = 'error';
            this.clearMessageAfterDelay();
            this.message = "Book not yet returned!"
            return;
        }
        if (book.returnApproved) {
            this.level = 'warning';
            this.clearMessageAfterDelay();
            this.message = "Book return already approved!"
        }
        this.bookService.approveBookReturn({"book-id": book.id as number}).subscribe({
            next: () => {
                this.level = 'success';
                this.findAllReturnedBooks();
                this.clearMessageAfterDelay();
                this.message = "Book return approved!"
            }
        })
    }

    private clearMessageAfterDelay() {
        setTimeout(() => {
            this.message = '';
        }, 5000);
    }
}









