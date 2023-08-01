import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';


interface SignUpResponse {
  headers: any;
  body: {
    responseMsg: string;
    token: string;
  };
  statusCode: string;
  statusCodeValue: number;
}
interface Role {
  roleId: number;
  roleName: string;
}
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent  {
  username: string ='';
  email: string='';
  password: string='';
  confirmPassword: string = '';
  phoneNumber: string='';
  roleName: string='';
 gender: string ='';

  constructor(private http: HttpClient, private router: Router,private snackBar: MatSnackBar) {}


  register(registerForm: any) {

    // Perform form validation
    if (registerForm && registerForm.invalid) {
      return;
    }
    // Perform form validation
    if (this.username && this.email && this.password && this.phoneNumber && this.roleName && this.gender) {
      // Prepare the data to be sent to the backend API
      const registerData = {
        userName: this.username,
        email: this.email,
        password: this.password,
        phoneNumber: this.phoneNumber,
        roleName: this.roleName,
        gender: this.gender

      };
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      // Call the backend API to save the registration form
        this.http.post<SignUpResponse>('http://localhost:9091/user/signup', registerData, { headers }).subscribe(
        (response) => {
          if(response.statusCodeValue === 201){
          console.log('Registration successful!', response.body.responseMsg);
          this.displaySuccessMessage(response.body.responseMsg);
          this.backtoLogin();
        }
        },
        (error) => {
        
          console.error('Registration failed!', error.error.responseMsg);
          if (error.status === 400) {
            if (error.error && error.error.responseMsg) {
              this.displaySignUpMessage(error.error.responseMsg, 'red');
            } else {
              this.displaySignUpMessage('Unauthorized: Invalid email or password', 'red');
            }
          } else if (error.status === 404) {
            this.displaySignUpMessage('Not Found: API endpoint not found', 'red');
          } else {
            this.displaySignUpMessage('An error occurred. Please try again later.', 'red');
          }
          // Handle the error response from the backend API
          // You can display an error message or perform other actions here
        }
      );
    }
  }

  displaySuccessMessage(message: string):void{
    this.snackBar.open(message, 'Close', {
      verticalPosition: 'top',
      duration: 3000, // Adjust the duration as needed
      panelClass: ['success-snackbar'] // Add custom CSS class for styling
    });
  }
  displaySignUpMessage(message: string, color: string) {
    const signUpMessage = document.getElementById('signupMessage');
    if (signUpMessage) {
      signUpMessage.textContent = message;
      signUpMessage.style.color = color;
    }
    // this.snackBar.open(message, 'Close', {
    //   duration: 3000, // Adjust the duration as per your requirement
    //   horizontalPosition: 'end',
    //   verticalPosition: 'top',
    //   panelClass: ['mat-toolbar', 'mat-primary'], // Adjust the CSS classes as needed
      
    // });
  }
  backtoLogin(){
    this.router.navigate(['/login']);
  }
}