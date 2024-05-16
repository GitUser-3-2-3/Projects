import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
    selector: 'app-saved-challenges',
    templateUrl: './saved-challenges.component.html',
    styleUrls: ['./saved-challenges.component.css']
})
export class SavedChallengesComponent implements OnInit {
    challenges: any[] = [];
    selectedChallenge: any = null;
    showUpdateForm = false;

    constructor(
        private fb: FormBuilder,
        private http: HttpClient,
        private snackBar: MatSnackBar,
        private dialog: MatDialog
    ) {
    }

    ngOnInit() {
        this.fetchChallenges();
    }

    updateToggle() {
        this.showUpdateForm = !this.showUpdateForm;
    }

    updateButton(challenge: any) {
        this.selectedChallenge = challenge;
    }

    updateChallenge() {
        /* const dialogRef = this.dialog.open(
             UpdateDialogComponent, {
                 width: '250px',
                 data: challenge
             }
         );

         dialogRef.afterClosed().subscribe(result => {
             if (result) {
                 this.selectedChallenge = result;
                 this.submitUpdate();
             }
         });*/

        if (this.updateDialog.valid) {
            this.selectedChallenge.month = this.updateDialog.get('month')?.value;
            this.selectedChallenge.description = this.updateDialog.get('description')?.value;

            this.submitUpdate();
        }
    }


    updateDialog = this.fb.group({
        month: ['',
            [
                Validators.required,
                Validators.minLength(3),
                Validators.maxLength(10)
            ]
        ],
        description: ['',
            [
                Validators.required,
                Validators.minLength(10),
                Validators.maxLength(50)
            ]
        ]
    });

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
