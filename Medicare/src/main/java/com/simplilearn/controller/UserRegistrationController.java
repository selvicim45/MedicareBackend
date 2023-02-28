package com.simplilearn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.entity.User;
import com.simplilearn.repository.UserRepository;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v2/")
public class UserRegistrationController {

	@Autowired
	private  UserRepository userRepository;
	
	@PostMapping("/register")
	private ResponseEntity<?> registerUser(@RequestBody User userData)
	{
		System.out.println("User Registration method called");
		if ((userData.getUsername() != " ") && (userData.getPassword() != ""))
		{
			User userDataFromDb= userRepository.findByusername(userData.getUsername());
			System.out.println("The db data is "+ userDataFromDb);
			if (userDataFromDb == null)
			{
			    userRepository.save(userData);
			    return ResponseEntity.ok(userData);
			}
		
		}
				System.out.println("error is " + (ResponseEntity<?>) ResponseEntity.internalServerError());
				return (ResponseEntity<?>) ResponseEntity.internalServerError();
			
		   
		}			
		     
		
	}


