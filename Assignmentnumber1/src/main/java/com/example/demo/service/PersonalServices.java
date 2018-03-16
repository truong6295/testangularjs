package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonalRepository;
import com.example.demo.entity.Personal;

@Service
public class PersonalServices {
	@Autowired
	private PersonalRepository personalRepository;
	
	//add student
	public Personal add(String address,Date birthday,String gender,String name) {
		Personal personals=new Personal(address, birthday, gender, name);
		if(personalRepository.save(personals) != null)
			return personals;
		else
			return null;
	}
	//update student
	public Personal update(int id,String address,Date birthday,String gender,String name) {
		Personal personals=new Personal(address, birthday, gender, name);
		personals.setPersonalId(id);
		if(personalRepository.save(personals)!=null)
			return personals;
		else
			return null;
	}
	//delete
	public boolean delete(int id) {
		if(personalRepository.exists(id)) {
			personalRepository.delete(id);
			return true;	
		}
		return false;
	}
	//view 
	public List<Personal> viewAll(){
		List<Personal> personals= personalRepository.findAll();
		return personals;
	}
	//view 1 entity
	public Personal viewId(int id) {
		Personal personals=personalRepository.findOne(id);
		return personals;
	}
}
