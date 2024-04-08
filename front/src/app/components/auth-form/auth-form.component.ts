import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-auth-form',
  templateUrl: './auth-form.component.html',
  styleUrls: ['./auth-form.component.scss']
})
export class AuthFormComponent implements OnInit {

  public isLogin: boolean = false;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.isLogin = this.route.snapshot.routeConfig?.path === 'connexion' && true

    console.log(this.route)
  
  }

}
