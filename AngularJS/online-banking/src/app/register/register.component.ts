import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators,AbstractControl, ValidatorFn, ValidationErrors } from '@angular/forms';
import { environment } from '../../environments/environment'

interface SignUpResponse {
  headers: any;
  body: {
    responseMsg: string;
    data: string;
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

export class RegisterComponent  implements OnInit {
  serviceUrl = environment.baseUrl;

  registerForm!: FormGroup;
  firstName: string ='';
  lastName: string ='';
  
  email: string='';
  password: string='';
  confirmPassword: string = '';
  phoneNumber: string='';
  roleName: string='';
  branch: string='';
  accountType: string='';
  openingBalance: string='';
 gender: string ='';
 selectedRole: string = 'User';
 accountTypes: any[] = [];
 branches: any[] = [];
  selectedAccountType: string = '';
  selectedBranch: string =';'
  branchName: string='';
  branchId: string ='';
  dateOfBirth: string ='';
  city: string =''
  occupation: string =''

  
  constructor(private http: HttpClient, private router: Router,private snackBar: MatSnackBar,private formBuilder: FormBuilder) {}

  ngOnInit() {
    // Initialize the registerForm in the ngOnInit hook
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.pattern('[0-9]{10}')]],
      branch: ['', Validators.required],
      accountType: ['', Validators.required],
      openingBalance: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(8)]],
      gender: ['', Validators.required]
    });
      this.getBranches();
      this.getAccountTypes();
  }
  
  // onRoleChange() {
  //   this.selectedRole = this.roleName;
  //   if (this.roleName === 'User') {
  //     // If the selected role is 'User', disable the Customer ID field and clear its value
  //     this.registerForm.get('customerId')?.disable();
  //     this.customerId = '';
  //   } else if (this.roleName === 'Admin') {
  //     // If the selected role is 'Admin', enable the Customer ID field
  //     this.registerForm.get('customerId')?.enable();
  //   }
  // }
  getAccountTypes() {
    this.http.get<any>(this.serviceUrl+'user/getAllAccTypes', {}).subscribe(
      types => {
          this.accountTypes = types;
        this.accountTypes.unshift({ accTypeId: 0, accTypeName: '--Select--' });
        this.selectedAccountType = '--Select--';

      },
      error => {
        console.error('Error fetching account types:', error);
      }
    );
  }

  getBranches() {
    this.http.get<any>(this.serviceUrl+'user/getAllBranches', {}).subscribe(
      branches => {
       this.branches = branches;
       this.branches.unshift({branchId: 0, branchName: '--Select--'});
       this.selectedBranch = '--Select--';
      },
      error => {
        console.error('Error fetching account types:', error);
      }
    );
  }

  register(registerForm: any) {

    // Perform form validation
    if (registerForm && registerForm.invalid) {
      return;
    }
    // Perform form validation
      // Prepare the data to be sent to the backend API
      const registerData = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        password: this.password,
        phoneNumber: this.phoneNumber,
        gender: this.gender,
        branch :this.branch,
        accountType: this.accountType,
        openingBalance:this.openingBalance,
        dob: this.dateOfBirth,
        city:this.city,
        occupation: this.occupation


      };
     
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      // Call the backend API to save the registration form
        this.http.post<SignUpResponse>(this.serviceUrl+'user/signup', registerData, { headers }).subscribe(
        (response) => {
          if(response.statusCodeValue === 201){
          console.log('Registration successful!', response.body.responseMsg);
          this.displaySuccessMessage(response.body.responseMsg, response.body.data);
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

  displaySuccessMessage(message: string, id:string):void{
    const messageWithId = message + '\nAccount Number: ' + id;
  this.snackBar.open(messageWithId, 'Close', {
    verticalPosition: 'top',
    duration: 0, // Set duration to 0 to prevent automatic closing
    panelClass: ['success-snackbar'], // Add custom CSS class for styling
    data: { id: id } // Use data property to store the ID
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