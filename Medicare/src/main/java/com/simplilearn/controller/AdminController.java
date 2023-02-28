package com.simplilearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.entity.Admin;

import com.simplilearn.repository.AdminRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v2/")
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@PostMapping("/admin")
	public ResponseEntity<?> loginUser(@RequestBody Admin adminData)
	{		
		System.out.println("Inside the Admin login method");
		//get id of data from database by passing username entered in front end textbox
		int adminid = adminRepository.findByadminname(adminData.getAdminname()).getId();

		//using the id found in previous step, get that user data into user object
           Admin admin = adminRepository.findById(adminid).orElseThrow();
		
		//Check whether the password entered in the front end and database match
		if (admin.getPassword().equals(adminData.getPassword()))
			return ResponseEntity.ok(admin);
		else
		{
			System.out.println("error is " + (ResponseEntity<?>) ResponseEntity.internalServerError());
			return (ResponseEntity<?>) ResponseEntity.internalServerError();
		}	
	

}
}
