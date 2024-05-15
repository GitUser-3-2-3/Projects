import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-saved-challenges',
    templateUrl: './saved-challenges.component.html',
    styleUrls: ['./saved-challenges.component.css']
})
export class SavedChallengesComponent implements OnInit {
    challenges: any[] = [];
    selectedChallenge: any = null;

    updateChallenge(challenge: any) {
        this.selectedChallenge = challenge;
    }

    constructor(private http: HttpClient, private snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.fetchChallenges();
    }

    fetchChallenges() {
        this.http.get<any[]>('http://localhost:8080/challenges').subscribe({
            next: response => {
                this.challenges = response;
                console.log(this.challenges);
            },
            error: error => {
                console.log(error);
                this.snackBar.open("Couldn't fetch challenges", 'Close', {
                    duration: 3000
                })
            }
        })
    }

    submitUpdate() {
        if (this.selectedChallenge) {
            this.http.put(
                'http://localhost:8080/challenges/' + this.selectedChallenge.id,
                this.selectedChallenge
            ).subscribe({
                next: response => {
                    console.log(response);
                    this.selectedChallenge = null;
                    this.snackBar.open('Challenge updated successfully', 'Close', {
                        duration: 3000
                    })
                },
                error: error => {
                    console.log('Error updating challenge', error);
                    this.snackBar.open("Couldn't update challenge", 'Close', {
                        duration: 3000
                    });
                }
            });
        }
    }

    // updateChallenge(challenge: any) {
    //     this.http.put(
    //         'http://localhost:8080/challenges/' + challenge.id, challenge
    //     ).subscribe({
    //         next: response => {
    //             console.log(response);
    //             this.snackBar.open('Challenge updated successfully', 'Close', {
    //                 duration: 3000
    //             });
    //         },
    //         error: error => {
    //             console.log(error);
    //             this.snackBar.open("Couldn't update challenge", 'Close', {
    //                 duration: 3000
    //             })
    //         }
    //     });
    // }

    deleteChallenge(challenge: any) {
        this.http.delete(
            'http://localhost:8080/challenges/' + challenge.id
        ).subscribe({
            next: response => {
                console.log(response);
                this.challenges = this.challenges.filter(ch => ch.id !== challenge.id);
                this.snackBar.open('Challenge deleted successfully', 'Close', {
                    duration: 3000
                });
            },
            error: error => {
                console.log(error);
                this.snackBar.open("Couldn't delete challenge", 'Close', {
                    duration: 3000
                });
            }
        })
    }
}
