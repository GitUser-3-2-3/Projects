import {Component, EventEmitter, Input, Output} from '@angular/core';
import {BookResponse} from "../../../../services/models/book-response";

@Component({
    selector: 'app-book-card',
    templateUrl: './book-card.component.html',
    styleUrls: ['./book-card.component.scss']
})
export class BookCardComponent {
    private _book: BookResponse = {};
    private _manage = false;

    get book(): BookResponse {
        return this._book;
    }

    @Input()
    set book(value: BookResponse) {
        this._book = value;
    }

    get manage(): boolean {
        return this._manage;
    }

    @Input()
    set manage(value: boolean) {
        this._manage = value;
    }

    get bookCover(): string | undefined {
        if (this._book.bookCover) {
            return 'data:image/jpg;base64, ' + this._book.bookCover;
        }
        return 'https://picsum.photos/1920/1080?random' + Math.random();
    }

    @Output() private share: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
    @Output() private archive: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
    @Output() private addToWaitingList: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
    @Output() private borrow: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
    @Output() private details: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
    @Output() private edit: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();

    onShowDetails() {
        return this.details.emit(this._book);
    }

    onBorrow() {
        return this.borrow.emit(this._book);
    }

    onAddToWaitingList() {
        return this.addToWaitingList.emit(this._book);
    }

    onEdit() {
        return this.edit.emit(this._book);
    }

    onShare() {
        return this.share.emit(this._book);
    }

    onArchive() {
        return this.archive.emit(this._book);
    }
}







