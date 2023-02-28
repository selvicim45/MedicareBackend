package com.simplilearn.repository;

import org.springframework.data.repository.CrudRepository;


import com.simplilearn.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByusername(String username);	

	
}
