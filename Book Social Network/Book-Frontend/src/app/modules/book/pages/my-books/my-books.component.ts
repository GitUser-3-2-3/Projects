import {Component, OnInit} from '@angular/core';
import {PageResponseBookResponse} from "../../../../services/models/page-response-book-response";
import {Router} from "@angular/router";
import {BookService} from "../../../../services/services/book.service";
import {BookResponse} from "../../../../services/models/book-response";

@Component({
    selector: 'app-my-books',
    templateUrl: './my-books.component.html',
    styleUrls: ['./my-books.component.scss']
})
export class MyBooksComponent implements OnInit {
    bookResponse: PageResponseBookResponse = {};
    page = 0;
    size = 5;

    constructor(
        private router: Router,
        private bookService: BookService
    ) {
    }

    ngOnInit(): void {
        this.findAllBooksByOwner();
    }

    private findAllBooksByOwner() {
        this.bookService.findAllBooksByOwner({page: this.page, size: this.size}).subscribe({
            next: (books => {
                this.bookResponse = books
            })
        })
    }

    goToFirstPage() {
        this.page = 0;
        this.findAllBooksByOwner();
    }

    goToPreviousPage() {
        this.page = this.page - 1;
        this.findAllBooksByOwner();
    }

    goToPage(index: number) {
        this.page = index;
        this.findAllBooksByOwner();
    }

    goToNextPage() {
        this.page = this.page + 1;
        this.findAllBooksByOwner();
    }

    goToLastPage() {
        this.page = this.bookResponse.totalPages as number - 1;
        this.findAllBooksByOwner();
    }

    get isLastPage(): boolean {
        return this.page == this.bookResponse.totalPages as number - 1;
    }

    archiveBook(book: BookResponse) {

    }

    editBook(book: BookResponse) {

    }

    shareBook(book: BookResponse) {

    }
}







