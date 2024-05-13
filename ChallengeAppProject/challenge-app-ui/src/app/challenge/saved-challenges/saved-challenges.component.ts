import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {catchError, throwError} from "rxjs";

@Component({
    selector: 'app-saved-challenges',
    templateUrl: './saved-challenges.component.html',
    styleUrls: ['./saved-challenges.component.css']
})
export class SavedChallengesComponent implements OnInit {
    challenges: any[] = [];
    showChallenges: boolean = false;

    constructor(private http: HttpClient, private snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.fetchChallenges();
    }

    fetchChallenges() {
        this.http.get<any[]>('http://localhost:8080/challenges').pipe(
            catchError(error => {
                console.error('Error:', error);
                return throwError(error);
            })
        ).subscribe({
            next: response => {
                this.challenges = response;
                console.log(this.challenges);
            },
            error: error => {
                console.log(error);
                this.snackBar.open("Couldn't fetch challenges", 'Close', {
                    duration: 3000,
                    panelClass: ['red-snackbar']
                })
            }
        })
    }

    toggleChallenges() {
        this.showChallenges = !this.showChallenges;
    }
}
