import { Component } from '@angular/core';
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

interface TransferResponse {
  headers: any;
  body: {
    responseMsg: string;
    id: string;
    obj: string;
  };
  statusCode: string;
  statusCodeValue: number;
}

interface PayResponse {
  headers: any;
  body: {
    responseMsg: string;
    id: string;
    obj: string;
  };
  statusCode: string;
  statusCodeValue: number;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent {

  fromAccountNo: string = '';
  mediumOfTrans: string = '';
  transAmount: string = '';
  toAccountNo: string = '';
  comments: string = '';
  mobileNumber: string ='';
  recharge_amount:string ='';
  EMI_Number:string ='';
  Loan_Amount:string ='';
  Card_Number:string ='';
  CVV:string ='';

  constructor(
    private http: HttpClient, 
    private router: Router, 
    private activatedRoute: ActivatedRoute,
     private snackBar: MatSnackBar, 
     private sharedDataService: SharedDataService)
     
     {
    this.email = this.sharedDataService.getEmail();
    this.username = this.sharedDataService.getUsername();
    this.phoneNumber = this.sharedDataService.getPhoneNumber();
    this.roleName = this.sharedDataService.getRoleName();
  }
  currentUserRole: string = '';
  currentPage = 1; // Current page number
  itemsPerPage = 5;
  username: string = '';
  email: string = '';
  phoneNumber: string = '';
  roleName: string = '';
  loginTime: string = '';
  currentTab: string = 'accounts';
  selectedTransactionType: string = '';
  selectedPaymentType: string = '';
  selectedAccountType: string = '';
  selectedAccount: any;
  showHistory: boolean = false;
  transactionHistory: any[] = [];
  transfer: any = {};
  Pay: any = {};
  loanPay: any = {};
  creditPay: any = {};
  accountTypes: any[] = [];
  accountNumbers: any[] = [];
  accounts: any[] = [
    {
      accountNumber: '1234567890',
      branch: 'Branch Name',
      name: 'Srini Vastava',
      balance: 49800
    },
    // Add more account details here
  ];
  showNewAccountForm: boolean = false;
  newAccount: any = {
    accountType: '',
    branch: '',
    name: '',
    initialDeposit: 0
  };

  showProfileDetails: boolean = false;
  hideProfileDetails() {
    this.showProfileDetails = false;
  }
  ngOnInit() {
    this.updateLoginTime();
    this.getAccountTypes();
  }

  openNewAccountForm() {
    this.redirectToNewAccountPage();
  }
  hideNewAccountForm() {
    this.showNewAccountForm = false;
  }
  submitNewAccountForm() {

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
  selectTab(tabName: string) {
    this.currentTab = tabName;

  }

  viewTransactionHistory() {

    this.showHistory = true;
    // Logic to fetch transaction history from the server and populate the transactionHistory array
    // For demonstration purposes, let's assume we have some dummy data here
    this.transactionHistory = [
      { id: '764536', type: 'NEFT', date: '2023-07-01', description: 'Grocery Shopping', debit: 5200, credit: 0, amount: 49800 },
      { date: '2023-06-28', description: 'Restaurant Bill', debit: 5000, credit: 0, amount: 55000 },
      { date: '2023-06-25', description: 'Utility Payment', debit: 0, credit: 60000, amount: 60000 },
      { date: '2023-06-25', description: 'Utility Payment', debit: 0, credit: 60000, amount: 60000 },
      { date: '2023-06-25', description: 'Utility Payment', debit: 0, credit: 60000, amount: 60000 },

    ];
  }
  getAccountTypes() {
    this.http.get<any>('http://localhost:9092/transfer/accTypes/all', {}).subscribe(
      response => {
        if (response.statusCodeValue === 200) {
          // Handle successful login response
          this.accountTypes = response.body.obj;
          this.accountTypes.unshift({ accTypeId: 0, accTypeName: '--Select--' });
          this.selectedAccountType = '--Select--';
          this.selectedAccountType = this.accountTypes[0].accTypeName;

        }
      },
      error => {
        console.error('Error fetching account types:', error);
      }
    );
  }
  onAccountTypeChange() {
    if (this.selectedAccountType !== '--Select--') {
      const selectedAccount = this.accountTypes.find(
        account => account.accTypeName === this.selectedAccountType
      );
      const accTypeId = selectedAccount.accTypeId;
      this.getAccountNumbersFromAPI(accTypeId);
    } else {
      this.accountNumbers = []; // Reset accountNumbers when the "Select Account Type" is chosen
    }
  }
  getAccountNumbersFromAPI(accTypeId: number) {
    this.http.get<any>(`http://localhost:9092/transfer/getAccNumbers?accTypeId=${accTypeId}`).subscribe(
      response => {

        this.accountNumbers = response.body.obj.map((account: any) => account.accNumber);
      },
      error => {
        console.error('Error fetching account numbers:', error);
      }
    );
  }

 clearForm(transferForm: NgForm) {
    transferForm.reset();
    this.transfer = {}; // Reset the transfer object as well
  }

  transferMoney(transferForm: any) {
    if (transferForm && transferForm.invalid) {
      return;
    }
         const transferData = {
        fromAccountNo: this.transfer.fromAccountNumber,
        toAccountNo: this.transfer.toAccountNumber,
        transAmount: this.transfer.amount,
        mediumOfTrans: this.selectedTransactionType,
        comments: this.transfer.comments
      };
      console.log("TransData: ", transferData);
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      this.http.post<TransferResponse>('http://localhost:9092/transfer/debit', transferData, { headers }).subscribe(
        (response) => {
          if (response.statusCodeValue === 200) {                  
            console.log('Registration successful!', response.body.responseMsg);
            this.displaySuccessMessage(response.body.responseMsg);
            this.transfer = {};
            this.fromAccountNo = '';
        this.mediumOfTrans = '';
        this.transAmount = '';
        this.toAccountNo = '';
        this.comments = '';
            this.currentTab = 'accounts';
            this.selectTab(this.currentTab);
          }
        },
        (error) => {

          console.error('Registration failed!', error.error.responseMsg);
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
          // Handle the error response from the backend API
          // You can display an error message or perform other actions here
        }
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
  redirectToLoginPage() {
    this.router.navigate(['/login']);
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
  redirectToNewAccountPage() {
    this.router.navigate(['/account']);
  }


  payNow(payform
    : any ) {

    // Perform form validation
    if (payform && payform.invalid) {
      return;
    }
    const payData = {
      recharge_amount : this.Pay.recharge_amount,
      mobileNumber : this.Pay.mobileNumber,
    };

    console.log("payNow: ", payData);
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });

      this.http.post<PayResponse>('http://localhost:9092/transfer/debit', payData, { headers }).subscribe(
        (response) => {
          if (response.statusCodeValue === 200) {                  
            console.log('Registration successful!', response.body.responseMsg);
            this.displaySuccessMessage(response.body.responseMsg);
            this.Pay = {};
        this.mobileNumber = '';
        this.recharge_amount = '';
            this.currentTab = 'billPayRecharge';
            this.selectTab(this.currentTab);
          }
        },
        (error) => {

          console.error('Bill Pay failed!', error.error.responseMsg);
          if (error.status === 400) {
            if (error.error && error.error.responseMsg) {
              this.displayLoginMessage(error.error.responseMsg, 'red');
            } else {
              this.displayLoginMessage('Unauthorized: mobile number', 'red');
            }
          } else if (error.status === 404) {
            this.displayLoginMessage('Not Found: API endpoint not found', 'red');
          } else {
            this.displayLoginMessage('An error occurred. Please try again later.', 'red');
          }
          // Handle the error response from the backend API
          // You can display an error message or perform other actions here
        }
      );
    }


    loan_Pay(loanpayform
      : any ) {
  
      // Perform form validation
      if (loanpayform && loanpayform.invalid) {
        return;
      }
      const loanpayData = {
        Loan_Amount: this.loanPay.Loan_Amount,
        EMI_Number : this.loanPay.EMI_Number,
      };
  
      console.log("loanPay: ", loanpayData);
        const headers = new HttpHeaders({
          'Content-Type': 'application/json'
        });
      }


      CreditCard_Pay(creditPayform
        : any ) {
    
        // Perform form validation
        if (creditPayform && creditPayform.invalid) {
          return;
        }
        const creditpayData = {
         CVV : this.creditPay.CVV,
          Card_Number : this.creditPay.Card_Number,
        };
    
        console.log("creditPay: ", creditpayData);
          const headers = new HttpHeaders({
            'Content-Type': 'application/json'
          });
        }
  }
    