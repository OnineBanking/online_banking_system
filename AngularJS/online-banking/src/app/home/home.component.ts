import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgForm } from '@angular/forms';
import { SharedDataService } from '../shared-data.service';
import { ObsService } from '../service/obs.service';
import { environment } from '../../environments/environment'

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
interface LoanApplyResponse {
  headers: any;
  body: {
    message: string;
    obj: {
      loanId: number;
    }
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

export class HomeComponent implements OnInit {

  serviceUrl = environment.baseUrl1;
  serviceUrl1 = environment.baseUrl;

  obs: string = '';
  fromAccountNo: string = '';
  mediumOfTrans: string = '';
  transAmount: string = '';
  toAccountNo: string = '';
  comments: string = '';

  constructor(
    private obsService: ObsService,
    private http: HttpClient,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private snackBar: MatSnackBar,
    private sharedDataService: SharedDataService
  ) {

    this.username = localStorage.getItem('userName') ?? '';
    this.phoneNumber = localStorage.getItem('mobile') ?? '';

  }
  currentUserRole: string = '';
  currentPage = 1; // Current page number
  itemsPerPage = 5;
  username: string = '';
  email: string = '';
  custId: string = '';
  phoneNumber: string = '';
  mobileNumber: string = '';
  roleName: string = '';
  loginTime: string = '';
  currentTab: string = 'accounts';
  selectedTransactionType: string = '';
  selectedPaymentType: string = '';
  selectedLoanType: string = '';
  selectedAccountType: string = '';
  selectedAccount: any;
  showHistory: boolean = false;
  transactionHistory: any[] = [];
  loanHistory: any[] = [];
  transfer: any = {};
  accountTypes: any[] = [];
  accountNumbers: any[] = [];
  amount: string = '';
  toAccountNumber: string = '';
  redirectTabAfterTransfer: string = ''
  recharge_amount: string = '';
  loanNumber: string = '';
  EmiAmount: string = '';
  Card_Number: string = '';
  CVV: string = '';
  Pay: any = {};
  loanPay: any = {};
  creditPay: any = {};
  accounts: any[] = [
    {
      accountNumber: localStorage.getItem('accNo') ?? '',
      branchName: localStorage.getItem('branchName') ?? '',
      name: localStorage.getItem('userName') ?? '',
      balance: localStorage.getItem('balance') ?? ''
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
  async ngOnInit() {

    this.loginTime = this.obsService.getLoginTime();
    this.getAccountTypes();
    this.refreshData();
    this.listOfLoans();

    const queryParams = this.activatedRoute.snapshot.queryParams;
    if (this.currentTab === 'accounts') {
      await this.obsService.getAccountInfo(); // Refresh account summary data
    }
  }

  openNewAccountForm() {
    this.redirectToNewAccountPage();
  }
  hideNewAccountForm() {
    this.showNewAccountForm = false;
  }
  submitNewAccountForm() {

  }



  selectTab(tabName: string) {
    this.currentTab = tabName;

  }
  async refreshData() {
    await this.obsService.getAccountInfo();
  }

  onLoanTypeChange() {
    if (this.selectedLoanType === 'loanList') {
      this.listOfLoans();
    }
  }
  async viewUserProfileDetails() {
    const userId = localStorage.getItem('userId') ?? '';
    this.http.get<any>(this.serviceUrl1 + `account/getAccInfoById?userId=${encodeURIComponent(userId)}`).subscribe(
      response => {
        if (response.statusCodeValue === 200) {
          this.phoneNumber = response.body.cust.phoneNumber;
          this.email = response.body.user.email;
          this.showProfileDetails = true;

        }

      },
      error => {
        // Handle login error
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

  calculateEmi() {
    const principal = this.loanApplication.loanAmount;
    const rate = this.loanApplication.interestRate / 100 / 12; // Monthly interest rate
    const time = this.loanApplication.termLength * 12; // Term in months

    if (principal && rate && time) {
      const emi = (principal * rate * Math.pow(1 + rate, time)) / (Math.pow(1 + rate, time) - 1);
      this.loanApplication.emiPerMonth = emi.toFixed(2);
    } else {
      this.loanApplication.emiPerMonth = null;
    }
  }

  loanApplication: any = {
    associatedAccount: '',
    loanAmount: null,
    interestRate: null,
    termLength: null,
    emiPerMonth: null,
  };

  applyForLoan(loanApplyForm: NgForm) {
    const loanData = {
      acnumber: this.loanApplication.associatedAccount,
      loan_amount: this.loanApplication.loanAmount,
      intrest: this.loanApplication.interestRate,
      ltenure: this.loanApplication.termLength * 12,
      lemi: this.loanApplication.emiPerMonth,
      custid: localStorage.getItem('custId') ?? '',
      status: 'open',
      bid: localStorage.getItem('branchId') ?? ''
    };
    console.log('loan data', loanData);
    this.http.post<LoanApplyResponse>('http://localhost:9093/loan/applyLoan', loanData)
      .subscribe(
        response => {

          if (response.statusCodeValue === 201) {
            this.displayLoanSuccessMessage(response.body.message, response.body.obj.loanId);
            // You can also reset the form here if needed
            loanApplyForm.reset();
            this.redirectTabAfterTransfer = 'loans';
            this.selectTab(this.redirectTabAfterTransfer);
          }
        },
        error => {
          // Handle error
          console.error('Error submitting loan application', error);
        }
      );
  }

  clearLoanApplyForm() {
    this.loanApplication = {
      associatedAccount: '',
      loanAmount: null,
      interestRate: null,
      termLength: null,
      emiPerMonth: null,
    };
  }

  listOfLoans() {
    const custId = localStorage.getItem('custId');

    fetch(`http://localhost:9093/loan/getloans/${custId}`)
      .then(response => response.json())
      .then((data: any) => {
        if (data.statusCodeValue === 201) {
          const loansList = (data.body.obj as Array<any>).map(loans => ({
            loanId: loans.loanId,
            loanAmount: loans.loan_amount,
            loanEmi: loans.loanEmi,
            loanInterest: loans.interest,
            laonTenure: loans.loanTenure,
            loanStatus: loans.status
          }));
          this.loanHistory = loansList;
        } else {
          console.error("Error fetching transaction history:", data.responseMsg);
        }
      })
      .catch(error => {
        console.error("Error fetching transaction history:", error);
      });

  }
  closeLoan(loanId: number) {
    this.http.put<any>(`http://localhost:9093/loan/closeloan/${loanId}`,{}).subscribe(
      response => {
        if (response.statusCodeValue === 201) {
          this.listOfLoans();
        }
      }
    );
  }

  viewTransactionHistory() {

    this.showHistory = true;

    const accNo = localStorage.getItem('accNo');

    fetch(this.serviceUrl + `transfer/transHistoryByid?accNo=${accNo}`)
      .then(response => response.json())
      .then((data: any) => {
        if (data.statusCode === "OK") {
          const transactions = (data.body.obj as Array<any>).map(transaction => ({
            id: transaction.transId,
            transType: transaction.transType,
            mediumOfTrans: transaction.mediumOfTrans,
            dot: transaction.dot.substring(0, 10),
            description: transaction.comments,
            debit: transaction.transType === "DEBIT" ? transaction.transAmount : 0,
            credit: transaction.transType === "CREDIT" ? transaction.transAmount : 0,
            transAmount: transaction.transAmount,
            accNumber: transaction.account.accNumber,
            comments: transaction.comments,
            balance: transaction.balance
          }));
          transactions.sort((a, b) => new Date(a.dot).getTime() - new Date(b.dot).getTime());
          transactions.reverse();
          this.transactionHistory = transactions;
        } else {
          console.error("Error fetching transaction history:", data.responseMsg);
        }
      })
      .catch(error => {
        console.error("Error fetching transaction history:", error);
      });
  }

  getAccountTypes() {
    this.http.get<any>(this.serviceUrl + 'transfer/accTypes/all', {}).subscribe(
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
    this.http.get<any>(this.serviceUrl + `transfer/getAccNumbers?accTypeId=${accTypeId}`).subscribe(
      response => {

        this.accountNumbers = response.body.obj.map((account: any) => account.accNumber);
      },
      error => {
        console.error('Error fetching account numbers:', error);
      }
    );
  }

  clearForm(transferForm: NgForm) {
    /// transferForm.reset();
    this.transfer = {
      fromAccountNo: '',
      toAccountNo: null,
      transAmount: null,
      mediumOfTrans: '',
      comments: null,
    }; // Reset the transfer object as well
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
      comments: this.transfer.comments,
      userId: localStorage.getItem('userId') ?? ''
    };

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.http.post<TransferResponse>(this.serviceUrl + 'transfer/debit', transferData, { headers }).subscribe(
      (response) => {
        if (response.statusCodeValue === 200) {
          this.displaySuccessMessage(response.body.responseMsg);
          this.refreshData();
          this.clearForm(transferForm);

          this.redirectTabAfterTransfer = 'accounts';
          this.selectTab(this.redirectTabAfterTransfer);
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
      }
    );

  }

  payNow(payform
    : any) {

    // Perform form validation
    if (payform && payform.invalid) {
      return;
    }
    const payData = {
      recharge_amount: this.Pay.recharge_amount,
      mobileNumber: this.Pay.mobileNumber,
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


  loan_Pay(loanpayform: any) {

    // Perform form validation
    if (loanpayform && loanpayform.invalid) {
      return;
    }
    const loanpayData = {
      lnumber:this.loanNumber,
      lemi: this.EmiAmount,
      acnumber: this.loanApplication.associatedAccount
    };
    console.log("loanPay: ", loanpayData);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.http.post<LoanApplyResponse>('http://localhost:9093/loan/payment', loanpayData, { headers }).subscribe(
      (response) => {
        if (response.statusCodeValue === 201) {
          this.displaySuccessMessage(response.body.message);
          this.refreshData();
          this.clearForm(loanpayform);

          this.redirectTabAfterTransfer = 'billPayRecharge';
          this.selectTab(this.redirectTabAfterTransfer);
        }
      },
      (error) => {

        console.error('Payment failed!', error.error.message);
        if (error.status === 400) {
          if (error.error && error.error.message) {
            this.displayLoginMessage(error.error.message, 'red');
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

  CreditCardApplication: any = {
    associatedAccount: '',
    custId: localStorage.getItem('custId')?? '',
    pan: null,
    income: null,
  };

  applyForCreditCard(CreditCardApplyForm: NgForm){
    const CreditCardApplication = {
      associatedAccount: this.CreditCardApplication.associatedAccount,
      custId: this.CreditCardApplication.custId,
      pan: this.CreditCardApplication.pan,
      income: this.CreditCardApplication.income,
      status: 'pending'
    };
    console.log('Credit card Application data', CreditCardApplication );
    this.http.post(this.serviceUrl+'applycreditCard/saveCreditCardApplication', CreditCardApplication)
      .subscribe(
        response => {
          // Handle success
          console.log('Credit Card application submitted successfully', response);
          // You can also reset the form here if needed
          CreditCardApplyForm.reset();
        },
        error => {
          // Handle error
          console.error('Error submitting loan application', error);
        }
      );
  }
  clearCreditCardApplyForm(){
    this.CreditCardApplication = {
      pan: null,
      income: null,
    };
  }

  CreditCardPymt: any ={
    associatedAccount: '',
    creditCardNumber: '',
    cvv: '',
    expMonth: '',
    expYear: '',
    amount: '',
    cardHolderName: '',
  };

  creditCardBill_Pay(creditPayform: any) {
    // Perform form validation
    if (creditPayform && creditPayform.invalid) {
      return;
    }
    const CreditCardPymt = {
      associatedAccount:this.CreditCardPymt.associatedAccount,
      creditCardNumber: this.CreditCardPymt.creditCardNumber,
      cvv: this.CreditCardPymt.cvv,
      expMonth: this.CreditCardPymt.expMonth,
      expYear: this.CreditCardPymt.expYear,
      amount: this.CreditCardPymt.amount,
      cardHolderName: this.CreditCardPymt.cardHolderName,
    };
    console.log("CreditCardPymt: ", CreditCardPymt);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.http.post(this.serviceUrl+'creditCardPymt/outStandingAmt', CreditCardPymt).subscribe(
      response => {
        // Handle success
        console.log('Credit Card Payment completed successfully', response);
        // You can also reset the form here if needed
        creditPayform.reset();
      },
      (error) => {

        console.error('Payment failed!', error.error.message);
        if (error.status === 400) {
          if (error.error && error.error.message) {
            this.displayLoginMessage(error.error.message, 'red');
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

  clearCreditCardPayForm(){
    this.CreditCardPymt={
      creditCardNumber: null,
      cvv: null,
      expMonth: null,
      expYear: null,
      amount: null,
      cardHolderName: null,
    };
  }

  logout() {

    this.http.post<LogoutResponse>(this.serviceUrl1 + 'user/signout', {}).subscribe(
      response => {
        if (response.statusCodeValue === 200) {
          // Handle successful login response
          this.displaySuccessMessage(response.body.responseMsg);
          this.redirectToLoginPage();
        }
      },
      error => {
        // Handle login error
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
  displayLoanSuccessMessage(message: string, id: number): void {
    const messageWithId = message + '\nLoan Number: ' + id;
    this.snackBar.open(messageWithId, 'Close', {
      verticalPosition: 'top',
      duration: 0, // Set duration to 0 to prevent automatic closing
      panelClass: ['success-snackbar'], // Add custom CSS class for styling
      data: { id: id } // Use data property to store the ID
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
}
