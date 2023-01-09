package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entity.Data;
import com.example.demo.Entity.Entry;
import com.example.demo.DTO.ResultDTO;
import com.example.demo.Service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

	@Autowired
	RestTemplate restTemplate;
	
	
	@GetMapping("/entries/{category}")
	public List<ResultDTO> getEntriesHandler(@PathVariable("category") String category){
		
		
		Data d= restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		
		List<Entry> entries= d.getEntries();
	
		List<ResultDTO> list = new ArrayList<>();

		for(Entry en:entries) {

			if(en.getCategory().equals(category))
				list.add(new ResultDTO(en.getApi(), en.getDescription()));
		}

		 
		return list;
		
	}
	@Autowired
	private EntityService entityService;
	@PostMapping("/entries")
	public ResponseEntity<String> saveEntriesHandler(@RequestBody Entry entry) {


		Data d= restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		List<Entry> entries= d.getEntries();
		entries.add(entry);
		String response = entityService.saveEntries(entries);

     return  new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
}
