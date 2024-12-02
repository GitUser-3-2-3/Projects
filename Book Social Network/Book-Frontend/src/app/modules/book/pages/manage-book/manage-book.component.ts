import {Component, OnInit} from '@angular/core';
import {BookRequest} from "../../../../services/models/book-request";
import {BookService} from "../../../../services/services/book.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'app-manage-book',
    templateUrl: './manage-book.component.html',
    styleUrls: ['./manage-book.component.scss']
})
export class ManageBookComponent implements OnInit {
    protected readonly Math = Math;
    validationErrMsg = {NotBlank: '', NoCover: ''}

    selectedBookCover: any;
    selectedPicture: string | undefined;

    bookRequest: BookRequest = {authorName: "", isbn: "", synopsis: "", title: ""};

    constructor(
        private router: Router,
        private bookService: BookService,
        private activatedRoute: ActivatedRoute
    ) {
    }

    ngOnInit(): void {
        const bookId = this.activatedRoute.snapshot.params['bookId'];
        if (bookId) {
            this.bookService.findBookById({'book-id': bookId}).subscribe({
                next: (book) => {
                    this.bookRequest = {
                        id: book.id, title: book.title as string,
                        authorName: book.authorName as string,
                        isbn: book.isbn as string,
                        synopsis: book.synopsis as string,
                        shareable: book.shareable
                    }
                    if (book.bookCover) {
                        this.selectedPicture = 'data:image/jpg;base64,' + book.bookCover;
                    }
                }
            })
        }
    }

    onFileSelected($event: any) {
        this.selectedBookCover = $event.target.files[0];
        console.log(this.selectedBookCover);

        if (this.selectedBookCover) {
            const reader = new FileReader();
            reader.onload = () => {
                this.selectedPicture = reader.result as string;
            }
            reader.readAsDataURL(this.selectedBookCover);
        }
    }

    saveBook() {
        this.validationErrMsg = {NotBlank: '', NoCover: ''}
        if (!this.selectedBookCover) {
            this.validationErrMsg.NoCover = 'Book cover is required';
            return;
        }
        this.bookService.saveBook({body: this.bookRequest}).subscribe({
            next: (bookId) => {
                this.bookService.uploadBookCover({
                    'book-id': bookId, body: {
                        bookCover: this.selectedBookCover
                    }
                }).subscribe({
                    next: () => {
                        this.router.navigate(['/books/my-books']).then(
                            r => console.log(r)
                        )
                    }
                })
            },
            error: (res) => {
                this.validationErrMsg.NotBlank = res.error.validationErrors.NotBlank;
            }
        })
    }
}









