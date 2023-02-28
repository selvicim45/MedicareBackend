package com.simplilearn.repository;

import org.springframework.data.repository.CrudRepository;

import com.simplilearn.entity.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer>{
	
	

	Admin findByadminname(String adminname);

}
