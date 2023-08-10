import { Component, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgForm } from '@angular/forms';
import { SharedDataService } from '../shared-data.service';
interface LogoutResponse {
  headers: any;
  body: {
    responseMsg: string;
    token: string;
  };
  statusCode: string;
  statusCodeValue: number;
}
@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent {

  constructor(private http: HttpClient, private router: Router, private activatedRoute: ActivatedRoute, private snackBar: MatSnackBar, private sharedDataService: SharedDataService) {
    this.email = this.sharedDataService.getEmail();
    this.username = this.sharedDataService.getUsername();
    this.phoneNumber = this.sharedDataService.getPhoneNumber();
  }
  username: string = '';
  email: string = '';
  phoneNumber: string = '';
  loginTime: string = '';
  showProfileDetails: boolean = false;

  showNewAccountForm: boolean = true;
  newAccount: any = {
    accountType: '',
    branch: '',
    name: '',
    initialDeposit: 0
  };
  hideProfileDetails() {
    this.showProfileDetails = false;
  }
  ngOnInit() {

    this.updateLoginTime();
  }
  updateLoginTime() {
    setInterval(() => {
      const currentDate = new Date();
      this.loginTime = this.formatTime(currentDate.getHours(), currentDate.getMinutes(), currentDate.getSeconds());
    }, 1000);
  }

  formatTime(hours: number, minutes: number, seconds: number): string {
    const ampm = hours >= 12 ? 'PM' : 'AM';
    const formattedHours = hours % 12 === 0 ? 12 : hours % 12;
    const formattedMinutes = minutes < 10 ? `0${minutes}` : minutes.toString();
    const formattedSeconds = seconds < 10 ? `0${seconds}` : seconds.toString();
    return `${formattedHours}:${formattedMinutes}:${formattedSeconds} ${ampm}`;
  }
  viewUserProfileDetails() {
    const url = `http://localhost:9091/user/getUserByName?username=${encodeURIComponent(this.email)}`;
    this.http.get<any>(url).subscribe(
      response => {
        if (response.statusCodeValue === 200) {
          console.log('User Info:', response.body.userName);
          this.phoneNumber = response.body.phoneNumber;
          this.showProfileDetails = true;
          
        }

      },
      
    );
  }
  logout() {

    this.http.post<LogoutResponse>('http://localhost:9091/user/signout', {}).subscribe(
      response => {
        if (response.statusCodeValue === 200) {
          // Handle successful login response
          console.log('Login Successful:', response.body.responseMsg);
          this.displaySuccessMessage(response.body.responseMsg);
          this.redirectToLoginPage();
        }
      },
      error => {
        // Handle login error
        console.error('Login Error:', error);
        if (error.status === 400) {
          if (error.error && error.error.responseMsg) {
            this.displayLoginMessage(error.error.responseMsg, 'red');
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
  redirectToLoginPage() {
    this.router.navigate(['/login']);
  }
  penNewAccountForm() {
    this.showNewAccountForm = true;
  }

  // Method to hide the new account form
  hideNewAccountForm() {
    this.router.navigate(['/home']);
  }

  // Method to submit the new account form
  submitNewAccountForm() {
    // Implement your logic here to handle the form submission
    // For example, you can send the form data to the server to create a new account
    // After successful account creation, you can update the UI as needed and reset the form fields
  }
}

