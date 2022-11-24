package com.masai.securitysec3.Repository;

import java.util.List;

import com.masai.securitysec3.Model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	List<Customer> findByEmail(String email);

}
