package com.simplilearn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.entity.User;
import com.simplilearn.repository.UserRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	public ResponseEntity<?> loginUser(@RequestBody User userData)
	{		
		System.out.println("Inside the method");
		System.out.println("user data is "+userData.getUsername());
		//get id of data from database by passing username entered in front end textbox
		int userid = userRepository.findByusername(userData.getUsername()).getUserid();

		//using the id found in previous step, get that user data into user object
           User user = userRepository.findById(userid).orElseThrow();
		
		//Check whether the password entered in the front end and database match
		if (user.getPassword().equals(userData.getPassword()))
			return ResponseEntity.ok(user);
		else
		{
			System.out.println("error is " + (ResponseEntity<?>) ResponseEntity.internalServerError());
			return (ResponseEntity<?>) ResponseEntity.internalServerError();
		}	
	
	}
	
	
}
