package com.simplilearn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.simplilearn.entity.Medicine;
import com.simplilearn.repository.MedicineRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v3/")
public class MedicineController {
	
	@Autowired
	private  MedicineRepository medicineRepository;
	
	//Method to get Medicine from Database
	@GetMapping("/medicine")
	public List<Medicine> listMedicine()
	{		
		List<Medicine> medicine = new ArrayList<>();
		
		medicineRepository.findAll().forEach(p -> medicine.add(p));
		
	    return medicineRepository.findByAscendingOrder(medicine);
	}
	//Method to get Medicine from Database By ID
	@GetMapping("/medicine/{id}")
	public Medicine displayById(@PathVariable Integer id) throws NotFoundException
	{
		return medicineRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	

	@GetMapping("/medicine/selection/{selection}")
	public List<Medicine> displayBySelection(@PathVariable String selection)
	{ 
		System.out.println("The selection variable is "+ selection);
		
		return medicineRepository.findAllBypdesc(selection);
		
	}
	
		
	//Method to Add New Medicine to List
	@PostMapping("/medicine")
	public Medicine addMedicine(@RequestBody Medicine medicinedata)
	{
		return medicineRepository.save(medicinedata);
	}
	
	//Method to Delete Medicine
	@DeleteMapping("/medicine/{id}")
	public void deleteMedicine(@PathVariable Integer id)
	{
		 System.out.println("Inside the Delete Method");
		 medicineRepository.deleteById(id);
	}
	
	//Method to Update Medicine
	@PutMapping("/medicine/{id}")
	public ResponseEntity<Medicine> updateMedicine(@PathVariable Integer id,@RequestBody Medicine medicineData) throws NotFoundException
	{
		
		 System.out.println("Update the Data Method Invoked");
		 String name = medicineData.getName();
		 String description = medicineData.getPdesc();
		 int price = medicineData.getPrice();
		 String Seller = medicineData.getSeller();
		 
		 Medicine medicine = medicineRepository.findById(id).orElseThrow(NotFoundException::new);
		 medicine.setName(name);
		 medicine.setPdesc(description);
		 medicine.setPrice(price);
		 medicine.setSeller(Seller);
		 
		 Medicine updatedMedicine =  medicineRepository.save(medicine);
		 return ResponseEntity.ok(updatedMedicine);
		
		 
	}
	
	
	
	

}
