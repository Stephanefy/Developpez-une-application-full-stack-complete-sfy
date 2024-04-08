import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.scss'],
  styles: [`
  mat-form-field {
    border-radius: 16px;
  }
  .mat-form-field-appearance-fill .mat-form-field-flex {
    border-radius: 16px;
  }
`],
encapsulation: ViewEncapsulation.None
})
export class CommentFormComponent implements OnInit {

  @Input() articleId!: number;

  public comment : {
    content: string,
    articleId: number,
    authorId: number
  } = {
    content: '',
    articleId: 0,
    authorId: 0
  }


  constructor() { }

  ngOnInit(): void {

    
  }


  submitForm(form: any): void {
    console.log(this.comment)
  }

}
