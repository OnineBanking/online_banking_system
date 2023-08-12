package com.olbs.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olbs.loan.dto.LoanRequestDto;
import com.olbs.loan.dto.ResponseDto;
import com.olbs.loan.entity.Account_info;
import com.olbs.loan.entity.Customer_Info;
import com.olbs.loan.entity.Loan;
import com.olbs.loan.service.LoanService;
import com.olbs.loan.service.iLoanService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/loan")
public class LoanApplicationController {

	@Autowired
	iLoanService service1;
	
	ResponseDto response = new ResponseDto();
	
	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer_Info(@RequestBody Customer_Info c) {
		Customer_Info customer = service1.addCustomner(c);
		response.setMessage("Customer Created Successfully!");
		response.setObj(customer);
		return ResponseEntity.ok().body(new ResponseEntity<>(response, HttpStatus.CREATED));
	}

	@PostMapping("/applyLoan")
	public ResponseEntity<?> applyLoan(@RequestBody LoanRequestDto loan) {
		Loan loanObj = service1.applyLoan(loan);
			response.setMessage("Loan Created Successfully!");
			response.setObj(loanObj);
			return ResponseEntity.ok().body(new ResponseEntity<>(response, HttpStatus.CREATED));
	}

	@GetMapping("/getloans/{id}")
	public ResponseEntity<?> getLoansByCustId(@PathVariable Long id) {
		List<Loan> loan = service1.getLoansByCustId(id);
		response.setMessage("Loan Details!");
		response.setObj(loan);
		return ResponseEntity.ok().body(new ResponseEntity<>(response, HttpStatus.CREATED));
	}

	@PutMapping("/closeloan/{id}")
	public ResponseEntity<?> closeLoan(@PathVariable Long id) {
		Loan loan = service1.closeLoan(id);
		response.setMessage("Loan closed!");
		response.setObj(loan);
		return ResponseEntity.ok().body(new ResponseEntity<>(response, HttpStatus.CREATED));
	}
	
	@PostMapping("/payment")
	public ResponseEntity<?> emiPayment(@RequestBody LoanRequestDto loan) {
		Account_info account=service1.emiPayment(loan);
		response.setMessage("Payment success!");
		response.setObj(account);
		return ResponseEntity.ok().body(new ResponseEntity<>(response, HttpStatus.CREATED));

	}
	
	

}
