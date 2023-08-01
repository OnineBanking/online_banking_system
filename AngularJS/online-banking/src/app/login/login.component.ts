import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SharedDataService } from '../shared-data.service';

interface LoginResponse {
  headers: any;
  body: {
    responseMsg: string;
    token: string;
  };
  statusCode: string;
  statusCodeValue: number;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  rememberMe: boolean = false;

  constructor(private http: HttpClient, private router: Router, private snackBar: MatSnackBar, private sharedDataService: SharedDataService) { }

  login() {
    const loginData = {
      email: this.email,
      password: this.password
    };
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.http.post<LoginResponse>('http://localhost:9091/user/login', loginData, { headers }).subscribe(
      response => {
        if (response.statusCodeValue === 200) {
          // Handle successful login response
          this.displaySuccessMessage(response.body.responseMsg);
          this.sharedDataService.setToken(response.body.token);
          this.fetchUserInfo(this.email);


        }
      },
      error => {
        // Handle login error
        console.error('Login Error:', error);
        if (error.status === 400) {
          if (error.error && error.error.responseMsg) {
            this.displayLoginMessage(error.error.responseMsg, 'red');
          } else {
            this.displayLoginMessage('Unauthorized: Invalid email or password', 'red');
          }
        } else if (error.status === 404) {
          this.displayLoginMessage('Not Found: API endpoint not found', 'red');
        } else {
          this.displayLoginMessage('An error occurred. Please try again later.', 'red');
        }
      }
    );
  }

  displaySuccessMessage(message: string): void {
    this.snackBar.open(message, 'Close', {
      verticalPosition: 'top',
      duration: 3000, // Adjust the duration as needed
      panelClass: ['success-snackbar'] // Add custom CSS class for styling
    });
  }
  displayLoginMessage(message: string, color: string) {
    const loginMessage = document.getElementById('loginMessage');
    if (loginMessage) {
      loginMessage.textContent = message;
      loginMessage.style.color = color;
    }

  }

  fetchUserInfo(username: string) {
    const url = `http://localhost:9091/user/getUserByName?username=${encodeURIComponent(username)}`;
    this.http.get<any>(url).subscribe(
      response => {
        if (response.statusCodeValue === 200) {
          this.sharedDataService.setUsername(response.body.userName);
          this.sharedDataService.setEmail(response.body.email);
          this.sharedDataService.setPhoneNumber(response.body.phoneNumber);
          this.sharedDataService.setRoleName(response.body.role.roleName);
          this.redirectToHomePage();
        }

      },
      error => {
        // Handle login error
        console.error('Login Error:', error);
        if (error.status === 400) {
          if (error.error && error.error.responseMsg) {
            this.displayLoginMessage(error.error.responseMsg, 'red');
          } else {
            this.displayLoginMessage('Unauthorized: Invalid email', 'red');
          }
        } else if (error.status === 404) {
          this.displayLoginMessage('Not Found: API endpoint not found', 'red');
        } else {
          this.displayLoginMessage('An error occurred. Please try again later.', 'red');
        }
      }
    );
  }
  redirectToRegister() {
    this.router.navigate(['/register']);
  }

  redirectToHomePage() {
    this.router.navigate(['/home']);
  }
}
