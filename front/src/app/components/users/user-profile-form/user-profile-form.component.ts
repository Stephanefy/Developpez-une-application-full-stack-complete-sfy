import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Store } from '@ngrx/store';
import { User } from 'src/app/interfaces/user.interface';
import { selectAuth } from 'src/app/state/auth.selectors';
import { StorageService } from 'src/app/services/local-storage.service';
import { UserApiService } from 'src/app/services/user-api.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-profile-form',
  templateUrl: './user-profile-form.component.html',
  styleUrls: ['./user-profile-form.component.scss']
})
export class UserProfileFormComponent implements OnInit {

  @Input() user!: User;
  @Output() updateSuccessEmitter = new EventEmitter<boolean>();
  public userInfoForm = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(4)]],
    email: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$/)]],
  });

  public userInfoData = {
    username: '',
    email: '',
  };

  constructor(
    private store: Store,
    private storageService: StorageService,
    private userApiService: UserApiService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.store.select(selectAuth).subscribe(value => {
      if (value.user) {
        // this.userInfoData.username = value.user.username;
        // this.userInfoData.email = value.user.sub;
        this.userInfoForm.get('username')?.setValue(value.user.username)
        this.userInfoForm.get('email')?.setValue(value.user.sub)

      }

    });
  
  }

  submitForm(form: any): void {

    if (!form.valid) {
      return;
    }

    const { username, email } = form.value;


    const requestBody = {
      username,
      email
    }

    console.log(requestBody)

    this.userApiService.update(this.user.userId, requestBody).subscribe({
      next: (response: User) => {
        this.storageService.getItem<User>('user')!.subscribe((value: User) => {
          value.username = response.username;
          value.sub = response.email;
          this.storageService.setItem('user', value);
        })

        this.setUpdateSuccess();
      },
      error: (error: any) => {
        console.error('Update user failed', error);
      }
    })
  }

  setUpdateSuccess(): void {
    this.updateSuccessEmitter.emit(true);
  }

}
