package com.olbs.loan.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.olbs.loan.dao.AccountRepositary;
import com.olbs.loan.dao.BranchRepository;
import com.olbs.loan.dao.CustomerRepository;
import com.olbs.loan.dao.LoanRepositary;
import com.olbs.loan.dao.TransactionRepositary;
import com.olbs.loan.dto.LoanRequestDto;
import com.olbs.loan.entity.Account_info;
import com.olbs.loan.entity.Branch;
import com.olbs.loan.entity.Customer_Info;
import com.olbs.loan.entity.Loan;
import com.olbs.loan.entity.Transaction_Info;
import com.olbs.loan.exceptions.AccountNotFoundException;
import com.olbs.loan.exceptions.CustomerNotFoundException;
import com.olbs.loan.exceptions.LoanNotFoundException;
@Service
public class LoanService implements iLoanService {
	
	@Autowired
	private CustomerRepository dao; 
	
	@Autowired
	private LoanRepositary loanDao;
	
	@Autowired
	private AccountRepositary accountDao;
	
	@Autowired
	private TransactionRepositary txnsDao;
	
	@Autowired
	BranchRepository branchRepo;

	@Override
	public Loan applyLoan(LoanRequestDto loan) {
		Loan loanDetails  = null; 
		Customer_Info cust = dao.findByCustId(loan.getCustid());
		
		Branch did = branchRepo.findByBranchId(loan.getBid());
		if(did == null) {
			throw new CustomerNotFoundException("Branch details not found");
		}
		if(cust!=null && did != null) {
			loanDetails  = new Loan();
			loanDetails.setLoanId(genarateNumber());
			loanDetails.setBid(did);
			loanDetails.setLoan_amount(loan.getLoan_amount());
			loanDetails.setLoanEmi(loan.getLemi());
			loanDetails.setLoanTenure(loan.getLtenure());
			loanDetails.setStatus(loan.getStatus());
			loanDetails.setInterest(loan.getIntrest());
			loanDetails.setCust(cust);
		}else {
			throw new CustomerNotFoundException("customer not found");
		}
			return loanDao.save(loanDetails);		
	}

	@Override
	public List<Loan> getLoansByCustId(Long custId) {
		Customer_Info cust = dao.findByCustId(custId);
		if(cust!=null) {
			return loanDao.getLoansByCustCustId(custId);
		}else {

		throw new CustomerNotFoundException("customer not found");
		}
	}

	@Override
	public Loan closeLoan(Long lnumber) {
		Loan loan=loanDao.getLoanByLoanId(lnumber);
		if(loan!=null) {
		loan.setStatus("Closed");
		return loanDao.save(loan);
		}else {
			throw new LoanNotFoundException("Loan Not Found");
		}	
	}

	@Override
	public Customer_Info addCustomner(Customer_Info customerinfo) {
		Customer_Info customer=dao.findByPhoneNumber(customerinfo.getPhoneNumber());
		if(customer==null) {
			return dao.save(customerinfo);
		}else {
			return customer;
		}
	}
	
	/*
	 * private double emiCalculator(LoanRequestDto loan) { double emi; double
	 * rate=10.0/(12*100); double time=loan.getLtenure()*12;
	 * if(loan.getLoan_amount()!=0) { emi=
	 * (loan.getLoan_amount()*rate*Math.pow(1+rate,time))/(Math.pow(1+rate,time)-1);
	 * return emi; } else { return 0; }
	 * 
	 * }
	 */

	@Override
	public Account_info emiPayment(LoanRequestDto loan) {
		Account_info account=null;
		Loan loanDetails=null;
		loanDetails=loanDao.getLoanByLoanId(loan.getLnumber());
		if(loanDetails==null) {
			throw new LoanNotFoundException("Loan details are not found for given loan Id:"+loan.getLnumber());
		}
		account=accountDao.findByAccNumber(loan.getAcnumber());
		if(account==null) {
			throw new AccountNotFoundException("Account Details Not found for given Account number:"+loan.getAcnumber());
		}
		int result = account.getOpeningBalance().compareTo(loan.getLemi());
		if(result >= 0) {
			BigDecimal openingbalance= (account.getOpeningBalance().subtract(loan.getLemi()));
			account.setOpeningBalance(openingbalance);
			BigDecimal emi= (loanDetails.getLoan_amount().subtract(loan.getLemi()));
			//int tenure=(int) (loanDetails.getLoanTenure()-1);
			loanDetails.setLoan_amount(emi);
			loanDetails.setLoanTenure(loanDetails.getLoanTenure()-1);
			saveTxns(account,loanDetails);
		    loanDao.save(loanDetails);
		    accountDao.save(account);
		    return account;
		}else {
			throw new AccountNotFoundException("insufficent balance in your account");
		}
	}
	
	private void saveTxns(Account_info acccount,Loan loan) {
		Transaction_Info txns=new Transaction_Info();
		txns.setTransId(genarateNumber());
		txns.setAccount(acccount);
		txns.setDot(new Date());
		txns.setMediumOfTrans("Online");
		txns.setTransType("Debit");
		txns.setTransAmount(loan.getLoanEmi());
		txns.setBalance(acccount.getOpeningBalance());
		txnsDao.save(txns);
	}
	
	private Long genarateNumber() {
        Random random = new Random();
        int randomNumber = 10000000 + random.nextInt(90000000);
        return Long.valueOf(randomNumber);
    }
	
}

