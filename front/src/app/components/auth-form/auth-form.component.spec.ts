import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AuthFormComponent } from './auth-form.component';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { provideMockStore, MockStore } from '@ngrx/store/testing';
import { ActivatedRoute } from '@angular/router';
import { Component } from '@angular/core';
import { register, login } from 'src/app/state/auth.actions';

@Component({
  selector: 'mat-error',
  template: '<ng-content></ng-content>'
})
export class testMatErrorComponent{
  constructor(){}
}

@Component({
  selector: 'mat-label',
  template: '<ng-content></ng-content>'
})
export class testMatLabelComponent{
  constructor(){}
}

@Component({
  selector: 'mat-form-field',
  template: '<ng-content></ng-content>'
})
export class testMatFormFieldComponent{
  constructor(){}
}



describe('AuthFormComponent', () => {
  let component: AuthFormComponent;
  let fixture: ComponentFixture<AuthFormComponent>;
  let store: MockStore;
  let route: ActivatedRoute;
  let spy: any;

  beforeEach(async () => {
    // Mock ActivatedRoute
    const routeMock = {
      snapshot: {
        routeConfig: {
          path: 'connexion',
        },
      },
    };

    await TestBed.configureTestingModule({
      declarations: [AuthFormComponent, testMatErrorComponent, testMatLabelComponent, testMatFormFieldComponent],
      imports: [
        ReactiveFormsModule,
      ],
      providers: [
        FormBuilder,
        provideMockStore({}),
        { provide: ActivatedRoute, useValue: routeMock },
        // Mock the Store if needed or use a StoreModule for testing
      ]
    }).compileComponents();

    store = TestBed.inject(MockStore);
    spy = spyOn(store, 'dispatch').and.callThrough(); // Spy on the store dispatch method
    fixture = TestBed.createComponent(AuthFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('form invalid when empty', () => {
    expect(component.authForm.valid).toBeFalsy();
  });

  it('should check password validity to be false', () => {

    component.authForm.controls['password'].setValue('12345');

    expect(component.authForm.controls['password'].valid).toBeFalsy();

  })

  // it('it should check email validity', () => {
  //   let email = component.authForm.controls['email'];
  //   expect(email.valid).toBeFalsy();

  //   let errors = email.errors || {};
  //   expect(errors['required']).toBeTruthy();

  //   email.setValue("test@example.com");
  //   errors = email.errors || {};
  //   expect(errors['required']).toBeFalsy();
  //   expect(email.valid).toBeTruthy();
  // });



  it('should dispatch register action if form is valid and isLogin is false', () => {
    component.isLoginForm = false;
    component.authForm.controls['username'].setValue('testuser');
    component.authForm.controls['email'].setValue('test@example.com');
    component.authForm.controls['password'].setValue('password123');


    component.submit();
    expect(spy).toHaveBeenCalledWith(register({
      username: 'testuser',
      email: 'test@example.com',
      password: 'password123'}));
  });

  it('should not dispatch register action if form is invalid', () => {
    component.authForm.controls['username'].setValue('');
    component.authForm.controls['email'].setValue('');
    component.authForm.controls['password'].setValue('');
    component.submit();
    
    expect(component.authForm.invalid).toBeTruthy();
    expect(spy).not.toHaveBeenCalledWith(register);
  });

  it('should not dispatch register action if form is invalid', () => {
    component.authForm.controls['usernameOrEmail'].setValue('');
    component.authForm.controls['password'].setValue('');
    component.submit();
    
    expect(component.authForm.invalid).toBeTruthy();
    expect(spy).not.toHaveBeenCalledWith(login);
  });
});