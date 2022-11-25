package com.masai.securitysec3.Controller;

import java.util.List;

import com.masai.securitysec3.Model.Cards;
import com.masai.securitysec3.Model.Customer;
import com.masai.securitysec3.Repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CardsController {
	
	@Autowired
	private CardsRepository cardsRepository;
	
	@GetMapping("/myCards")
	public List<Cards> getCardDetails(@RequestParam(value = "Id", required = false) int Id) {
		List<Cards> cards = cardsRepository.findByCustomerId(Id);
		if (cards != null ) {
			return cards;
		}else {
			return null;
		}
	}

}
