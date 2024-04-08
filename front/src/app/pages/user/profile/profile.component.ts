import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Theme } from 'src/app/interfaces/theme.interface';
import { User } from 'src/app/interfaces/user.interface';
import { UserApiService } from 'src/app/services/user-api.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {


  // public user$ = new Observable();
  public user$: Observable<User> = this.userApiService.detail("1");
  public subscriptions$: Observable<Theme[]> = this.userApiService.getSubscriptions("1");


  constructor(private userApiService: UserApiService) { }

  ngOnInit(): void {
  }

}
