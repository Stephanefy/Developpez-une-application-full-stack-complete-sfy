import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideMockStore } from '@ngrx/store/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { UserProfileFormComponent } from './user-profile-form.component';
import { FormBuilder } from '@angular/forms';
import { MatFormField } from '@angular/material/form-field';
import { MaterialModule } from 'src/app/material/material.module';
import { Component } from '@angular/core';

@Component({
  selector: 'mat-form-field',
  template: '<ng-content></ng-content>'
})
export class testMatFormFieldComponent{
  constructor(){}
}


describe('UserProfileFormComponent', () => {
  let component: UserProfileFormComponent;
  let fixture: ComponentFixture<UserProfileFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserProfileFormComponent, testMatFormFieldComponent ],
      imports: [HttpClientTestingModule],
      providers: [provideMockStore({}), FormBuilder ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserProfileFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
