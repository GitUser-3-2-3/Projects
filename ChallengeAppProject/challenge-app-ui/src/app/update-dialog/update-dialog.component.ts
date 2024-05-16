import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
    selector: 'app-update-dialog',
    templateUrl: './update-dialog.component.html',
    styleUrls: ['./update-dialog.component.css']
})
export class UpdateDialogComponent {
    constructor(
        private fb: FormBuilder,
        public dialogRef: MatDialogRef<UpdateDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any
    ) {
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
    })

    onNoClick(): void {
        this.dialogRef.close();
    }
}
