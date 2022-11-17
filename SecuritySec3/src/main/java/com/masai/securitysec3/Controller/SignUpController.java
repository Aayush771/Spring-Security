package com.masai.securitysec3.Controller;

import com.masai.securitysec3.Model.Customer;
import com.masai.securitysec3.Repository.CustomerDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    @Autowired
    CustomerDAO customerDAO;
    @PostMapping("/register")
   public ResponseEntity<String> registerUser(@RequestBody Customer customer){
      Customer savedCustomer = null;
      ResponseEntity responseEntity = null;
      try{
          savedCustomer = customerDAO.save(customer);
          if(savedCustomer.getId()>0){
              responseEntity = ResponseEntity.status(HttpStatus.CREATED)
                      .body("User Successfully registered");
          }
      }catch (Exception exception){
          responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body("Exception occurs due to "+exception.getMessage());
      }
      return responseEntity;
   }
}
