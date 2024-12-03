import {Component, OnInit} from '@angular/core';
import {BookService} from "../../../../services/services/book.service";
import {Router} from "@angular/router";
import {PageResponseBookResponse} from "../../../../services/models/page-response-book-response";
import {BookResponse} from "../../../../services/models/book-response";

@Component({
    selector: 'app-book-list',
    templateUrl: './book-list.component.html',
    styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {
    bookResponse: PageResponseBookResponse = {};
    page = 0;
    size = 10;
    message = '';
    level = 'success'

    constructor(
        private router: Router,
        private bookService: BookService
    ) {
    }

    ngOnInit(): void {
        this.findAllBooks();
    }

    private findAllBooks() {
        this.bookService.findAllBooks({page: this.page, size: this.size}).subscribe({
            next: (books => {
                this.bookResponse = books
            })
        })
    }

    goToFirstPage() {
        this.page = 0;
        this.findAllBooks();
    }

    goToPreviousPage() {
        this.page = this.page - 1;
        this.findAllBooks();
    }

    goToPage(index: number) {
        this.page = index;
        this.findAllBooks();
    }

    goToNextPage() {
        this.page = this.page + 1;
        this.findAllBooks();
    }

    goToLastPage() {
        this.page = this.bookResponse.totalPages as number - 1;
        this.findAllBooks();
    }

    get isLastPage(): boolean {
        return this.page == this.bookResponse.totalPages as number - 1;
    }

    borrowBook(res: BookResponse) {
        this.bookService.borrowBook({'book-id': res.id as number}).subscribe({
            next: () => {
                this.level = 'success';
                this.clearMessageAfterDelay();
                this.message = 'Book borrowed successfully';
            }, error: (err) => {
                this.level = 'error';
                this.clearMessageAfterDelay()
                this.message = err.error.errorBody;
            }
        });
    }

    private clearMessageAfterDelay() {
        setTimeout(() => {
            this.message = '';
        }, 5000);
    }
}







