import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { CommentApiService } from 'src/app/services/comment-api.service';
import { selectAuth } from 'src/app/state/auth.selectors';

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

  private authorId!: number;

  public commentForm!: FormGroup 

  public comment : {
    content: string,
   
  } = {
    content: '',
  
  }


  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private store: Store,
    private commentApiService: CommentApiService
  ) { }

  ngOnInit(): void {

    this.commentForm = this.fb.group({
      content: [''],
    });

    this.store.select(selectAuth).subscribe(value => {
      //@ts-ignore
      this.authorId =  value.user!.userId
    
  });
  }


  submitForm(form: any): void {
    const requestData = {
      content: this.comment.content,
      authorId: this.authorId
    }

      this.commentApiService.create(requestData, this.articleId.toString()).subscribe({
      next: (response) => {
        console.log(response)
      },
      error: (error) => {
        console.error(error)
      }
    })

  }

}
