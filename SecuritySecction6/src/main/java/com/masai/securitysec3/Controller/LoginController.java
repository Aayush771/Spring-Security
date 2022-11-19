package com.masai.securitysec3.Controller;


import java.security.Principal;
import java.util.Date;
import java.util.List;

import com.masai.securitysec3.Model.Customer;
import com.masai.securitysec3.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer){
		Customer savedCustomer = null;
		ResponseEntity responseEntity = null;
		try {
			System.out.println(customer.getPwd());
			String hashpwd = passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashpwd);
			System.out.println(hashpwd);
		savedCustomer = customerRepository.save(customer);
		if(savedCustomer.getId()>0){
			responseEntity  = ResponseEntity.
					status(HttpStatus.CREATED)
					.body("User Successfully registered");
		}
		}catch (Exception e){
			responseEntity = ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Excetion Occured "+e.getMessage());
		}
		return responseEntity;
	}
	@RequestMapping("/user")
	public Customer getUserDetailsAfterLogin(Principal user) {
		List<Customer> customers = customerRepository.findByEmail(user.getName());
		if (customers.size() > 0) {
			return customers.get(0);
		}else {
			return null;
		}
		
	}

}
