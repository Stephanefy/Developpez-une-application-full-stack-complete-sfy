import { Component, Input, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { User } from 'src/app/interfaces/user.interface';
import { selectAuth } from 'src/app/state/auth.selectors';
import { StorageService } from '../../../../../.history/src/app/services/local-storage.service_20240410083239';

@Component({
  selector: 'app-user-profile-form',
  templateUrl: './user-profile-form.component.html',
  styleUrls: ['./user-profile-form.component.scss']
})
export class UserProfileFormComponent implements OnInit {

  @Input() user!: User
  

  public userInfoData = {
    username: '',
    email: '',
  };

  constructor(
    private store: Store,
    private storageService: StorageService
  ) { }

  ngOnInit(): void {
    console.log(this.user.email)
    this.store.select(selectAuth).subscribe(value => {
      if (value.user) {
        this.userInfoData.username = value.user.username;
        //@ts-ignore
        this.userInfoData.email = value.user.sub;
      }
    });
    // this.store.select(selectAuth).subscribe(value => {
    //   this.userInfoData.username = value.user?.username!
    //   this.userInfoData.email = value.user?.email!
    // })
  }

  submitForm(form: any): void {
    console.log(this.user)
  }

}
