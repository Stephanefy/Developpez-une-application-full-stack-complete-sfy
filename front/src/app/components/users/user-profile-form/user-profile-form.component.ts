import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/interfaces/user.interface';

@Component({
  selector: 'app-user-profile-form',
  templateUrl: './user-profile-form.component.html',
  styleUrls: ['./user-profile-form.component.scss']
})
export class UserProfileFormComponent implements OnInit {

  @Input() user!: User | null
  

  public userInfoData = {
    username: '',
    email: ''
  };

  constructor() { }

  ngOnInit(): void {
  }

  submitForm(form: any): void {
    console.log(this.user)
  }

}
