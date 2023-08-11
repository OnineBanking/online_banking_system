package com.olbs.loan.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.olbs.loan.dao.AccountRepositary;
import com.olbs.loan.dao.CustomerRepository;
import com.olbs.loan.dao.LoanRepositary;
import com.olbs.loan.dao.TransactionRepositary;
import com.olbs.loan.dto.LoanRequestDto;
import com.olbs.loan.entity.Account_info;
import com.olbs.loan.entity.Customer_Info;
import com.olbs.loan.entity.Loan;
import com.olbs.loan.entity.Transaction_Info;
import com.olbs.loan.exceptions.AccountNotFoundException;
import com.olbs.loan.exceptions.CustomerNotFoundException;
import com.olbs.loan.exceptions.LoanNotFoundException;
@Component("loanService")
public class LoanService implements iLoanService {
	
	@Autowired
	private CustomerRepository dao; 
	
	@Autowired
	private LoanRepositary loanDao;
	
	@Autowired
	private AccountRepositary accountDao;
	
	@Autowired
	private TransactionRepositary txnsDao;

	@Override
	public Loan applyLoan(LoanRequestDto loan) {
		Loan loanDetails  = null; 
		Customer_Info cust = dao.findByCustid(loan.getCustId());
		if(cust!=null) {
			loanDetails  = new Loan();
			loanDetails.setBid(loan.getBid());
			loanDetails.setLoan_amount(loan.getLoan_amount());
			loanDetails.setLemi(loan.getLemi());
			loanDetails.setLtenure(loan.getLtenure());
			loanDetails.setStatus(loan.getStatus());
			loanDetails.setCust(cust);
		}else {
			throw new CustomerNotFoundException("customer not found");
		}
		   System.out.println("loan details"+loanDetails.getLnumber());
			return loanDao.save(loanDetails);		
	}

	@Override
	public List<Loan> getLoansByCustId(int custId) {
		Customer_Info cust = dao.findByCustid(custId);
		if(cust!=null) {
			return loanDao.getLoansByCustCustid(custId);
		}else {

		throw new CustomerNotFoundException("customer not found");
		}
	}

	@Override
	public Loan closeLoan(int lnumber) {
		Loan loan=loanDao.getLoanBylnumber(lnumber);
		if(loan!=null) {
		loan.setStatus("Closed");
		return loanDao.save(loan);
		}else {
			throw new LoanNotFoundException("Loan Not Found");
		}	
	}

	@Override
	public Customer_Info addCustomner(Customer_Info customerinfo) {
		Customer_Info customer=dao.findByMobileno(customerinfo.getMobileno());
		if(customer==null) {
			return dao.save(customerinfo);
		}else {
			return customer;
		}
	}
	
	private double emiCalculator(LoanRequestDto loan) {
		double emi;
		double rate=10.0/(12*100);
        double time=loan.getLtenure()*12;
		if(loan.getLoan_amount()!=0) {
			emi= (loan.getLoan_amount()*rate*Math.pow(1+rate,time))/(Math.pow(1+rate,time)-1);
			return emi;
		}	else {
			return 0;
		}
		
	}

	@Override
	public Account_info emiPayment(LoanRequestDto loan) {
		Account_info account=null;
		Loan loanDetails=null;
		loanDetails=loanDao.getLoanBylnumber(loan.getLnumber());
		if(loanDetails==null) {
			throw new LoanNotFoundException("Loan details are found for given loan Id:"+loan.getLnumber());
		}
		account=accountDao.getAccountByacnumber(loan.getAcnumber());
		if(account==null) {
			throw new AccountNotFoundException("Account Details Not found for given Account number:"+loan.getAcnumber());
		}
		if(account.getOpening_balance() >= loan.getLemi()) {
			int openingbalance=(int) (account.getOpening_balance()-loan.getLemi());
			account.setOpening_balance(openingbalance);
			int emi=(int) (loan.getLoan_amount()-loan.getLemi());
			int tenure=(int) (loan.getLtenure()-1);
			loanDetails.setLoan_amount(emi);
			loanDetails.setLtenure(tenure);
			saveTxns(account,loan);
		    loanDao.save(loanDetails);
		    accountDao.save(account);
		    return account;
		}else {
			throw new AccountNotFoundException("insufficent balance in your account");
		}
	}
	
	private void saveTxns(Account_info acccount,LoanRequestDto loan) {
		Transaction_Info txns=new Transaction_Info();
		txns.setTnumber(genarateNumber());
		txns.setAcnumber(acccount.getAcnumber());
		txns.setDot(new Date());
		txns.setMedium_of_transaction("Online");
		txns.setTransaction_type("Debit");
		txns.setTransaction_amount(loan.getLemi());
		txnsDao.save(txns);
	}
	
	private Long genarateNumber() {
        Random random = new Random();
        int randomNumber = 10000000 + random.nextInt(90000000);
        return Long.valueOf(randomNumber);
    }
	
}

