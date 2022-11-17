package com.masai.securitysec3.Config;

import com.masai.securitysec3.Model.Customer;
import com.masai.securitysec3.Model.SecurityCustomer;
import com.masai.securitysec3.Repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SecurityUserDetaisl implements UserDetailsService {
    @Autowired
   private CustomerDAO customerDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customer = customerDAO.findByEmail(username);

        if (customer.size() == 0) {
            throw new UsernameNotFoundException("User details not found for the user : " + username);
        }
        return new SecurityCustomer(customer.get(0));
    }
}
