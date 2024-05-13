import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-challenge',
    templateUrl: './challenge.component.html',
    styleUrls: ['./challenge.component.css']
})
export class ChallengeComponent implements OnInit {
    constructor(
        private fb: FormBuilder,
        private http: HttpClient,
        private snackBar: MatSnackBar
    ) {
    }

    ngOnInit() {
        // does something
    }

    challengeForm = this.fb.group({
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

    addChallenge(challenge: any) {
        return this.http.post('http://localhost:8080/challenges', challenge);
    }

    onSubmit() {
        if (this.challengeForm.valid) {
            this.addChallenge(this.challengeForm.value).subscribe({
                next: response => {
                    console.log(response);
                    this.snackBar.open('Challenge added successfully', 'Close', {
                        duration: 3000,
                        panelClass: ['green-snackbar']
                    });
                    this.challengeForm.reset();
                },
                error: error => {
                    console.log(error);
                    this.snackBar.open("Couldn't add challenge", 'Close', {
                        duration: 3000,
                        panelClass: ['red-snackbar']
                    })
                }
            });
        }
    }
}
