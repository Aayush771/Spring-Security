package com.masai.securitysec3.Controller;

import com.masai.securitysec3.Model.Accounts;
import com.masai.securitysec3.Model.Customer;
import com.masai.securitysec3.Repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@GetMapping("/myAccount")
	public Accounts getAccountDetails(@RequestParam(value = "Id", required = false) Integer Id) {
		Accounts accounts = accountsRepository.findByCustomerId(Id);
		if (accounts != null ) {
			return accounts;
		}else {
			return null;
		}
	}

}
