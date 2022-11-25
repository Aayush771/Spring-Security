package com.masai.securitysec3.Controller;

import java.util.List;

import com.masai.securitysec3.Model.AccountTransactions;
import com.masai.securitysec3.Model.Customer;
import com.masai.securitysec3.Repository.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BalanceController {

	@Autowired
	private AccountTransactionsRepository accountTransactionsRepository;
	
	@GetMapping ("/myBalance")
	public List<AccountTransactions> getBalanceDetails(@RequestParam (value = "Id", required = false) int Id) {
		List<AccountTransactions> accountTransactions = accountTransactionsRepository.
				findByCustomerIdOrderByTransactionDtDesc(Id);
		if (accountTransactions != null ) {
			return accountTransactions;
		}else {
			return null;
		}
	}
}
