package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entity.Personal;
import com.example.demo.service.PersonalServices;


@RestController
@RequestMapping("/api")
public class PersonalController {
	private static final SimpleDateFormat formatHHmm = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private PersonalServices personalService;
  //view all
    @RequestMapping(value = "/personal/", method = RequestMethod.GET)
    public ResponseEntity<List<Personal>> listAllPersonal() {
        List<Personal> personals = personalService.viewAll();
        if (personals.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Personal>>(personals, HttpStatus.OK);
    }
    //view 1 entity
    @RequestMapping(value = "/personal/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPersonal(@PathVariable("id") int id) {
    	Personal personals = personalService.viewId(id);
        return new ResponseEntity<Personal>(personals, HttpStatus.OK);
    }
    //creat
    @RequestMapping(value = "/personal/",method = RequestMethod.POST)
    public ResponseEntity<?> creatPersonal(Personal personal, UriComponentsBuilder ucBuilder) throws ParseException {
        Personal personals=personalService.add(personal.getAddress(), personal.getBirthday(), personal.getGender(), personal.getName());
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/personal/{id}").buildAndExpand(personals.getPersonalId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    //update
    @RequestMapping(value = "/personal/", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePersonal(Personal personal) throws ParseException{
        Personal currentPersonal=personalService.update(personal.getPersonalId(), personal.getAddress(), personal.getBirthday(), personal.getGender(), personal.getName());
        return new ResponseEntity<Personal>(currentPersonal, HttpStatus.OK);
    }
    //delete 1 entity
    @RequestMapping(value = "/personal/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePersonal(@PathVariable("id") int personalId) {
        if(personalService.delete(personalId)) {
        	
        	return new ResponseEntity<Personal>(HttpStatus.NO_CONTENT);
        }
        else
        	return new ResponseEntity("no delete personal with id {} khong co.",HttpStatus.NOT_FOUND);
    }
}
