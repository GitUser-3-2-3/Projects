import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";

@Component({
    selector: 'app-challenge',
    templateUrl: './challenge.component.html',
    styleUrls: ['./challenge.component.css']
})
export class ChallengeComponent implements OnInit {
    constructor(private fb: FormBuilder) {
    }

    ngOnInit() {
    }

    challengeForm = this.fb.group({
        month: ['',
            [Validators.required, Validators.minLength(3), Validators.maxLength(10)]
        ],
        description: ['',
            [Validators.required, Validators.minLength(10), Validators.maxLength(50)]
        ]
    });

    onSubmit() {
        console.log(this.challengeForm.valid);
    }
}
