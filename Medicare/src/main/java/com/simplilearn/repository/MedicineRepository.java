package com.simplilearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.entity.Medicine;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine,Integer> {

	 
	List<Medicine> findAllBypdesc(String selection);
	
	@Query(value = "SELECT m FROM Medicine as m order by m.name")
	List<Medicine> findByAscendingOrder(List<Medicine> medicine);

	
	
	

}
