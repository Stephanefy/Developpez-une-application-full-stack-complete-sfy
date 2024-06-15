import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { CreateFormComponent } from './create-form.component';
import { FormBuilder } from '@angular/forms';
import { MatError } from '@angular/material/form-field';
import { Component } from '@angular/core';

@Component({
  selector: 'mat-error',
  template: '<ng-content></ng-content>'
})
export class testMatErrorComponent{
  constructor(){}
}


describe('CreateFormComponent', () => {
  let component: CreateFormComponent;
  let fixture: ComponentFixture<CreateFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateFormComponent, testMatErrorComponent ],
      imports: [
        HttpClientTestingModule,
      ],
      providers: [FormBuilder]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
