package com.example.demo.controller;



import java.sql.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Contact;
import com.example.demo.repo.ContactRepo;

@RestController
public class ContactController {

	@Autowired
	private ContactRepo contactRepo;
	
	@GetMapping("/contactUs")
	public String myAccount()
	{
		return "contact our bank for all details";
	}
	
	@PostMapping("/contact")
	public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
		contact.setContactId(getServiceReqNumber());
		contact.setCreateDt(new Date(System.currentTimeMillis()));
		return contactRepo.save(contact);
	}

	public String getServiceReqNumber() {
	    Random random = new Random();
	    int ranNum = random.nextInt(999999999 - 9999) + 9999;
	    return "SR"+ranNum;
	}
}


