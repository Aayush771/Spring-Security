package com.masai.securitysec3.Repository;

import com.masai.securitysec3.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerDAO extends JpaRepository<Customer,Integer> {
    List<Customer> findByEmail(String email);
}
