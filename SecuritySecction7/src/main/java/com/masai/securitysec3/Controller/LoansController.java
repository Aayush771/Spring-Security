package com.masai.securitysec3.Controller;

import java.util.List;

import com.masai.securitysec3.Model.Customer;
import com.masai.securitysec3.Model.Loans;
import com.masai.securitysec3.Repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoansController {
	
	@Autowired
	private LoanRepository loanRepository;
	
	@GetMapping("/myLoans")
	public List<Loans> getLoanDetails(@RequestParam(value = "Id", required = false) int Id) {
		List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(Id);
		if (loans != null ) {
			return loans;
		}else {
			return null;
		}
	}

}
