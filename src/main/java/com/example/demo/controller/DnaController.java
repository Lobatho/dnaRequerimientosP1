package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Dna;
import com.example.demo.entity.DnaArray;
import com.example.demo.model.AjaxResponseBody;
import com.example.demo.model.AjaxResponseBodyObj;
import com.example.demo.model.MDna;
import com.example.demo.service.DnaService;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
@RequestMapping("/v1")
public class DnaController {
	
	@Autowired
	@Qualifier("service")
	DnaService service;

	@PostMapping("/mutation/")
	public ResponseEntity dnaMutation(@RequestBody @Validated DnaArray dna) {
		if(service.hasMutation(dna.getDna()))
		return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.FORBIDDEN);
		
	}
	
	@PutMapping("/dna/{name}")
	public boolean dnaRegister(@PathVariable("name") String nameNewHuman) {
		
		return service.registerNew(nameNewHuman);
	}
	
	@GetMapping("/stats")
	public AjaxResponseBodyObj sayHello() {
	    return service.getMutationStats();
	}
	
	@GetMapping("/getMutation/{mutation}")
	public AjaxResponseBody getDnas(@PathVariable("mutation") String mutation){
		return service.getMutation(mutation);
	}
	
	
	@GetMapping("/getAll/")
	public AjaxResponseBody getAllDnasTask(){
		return service.getAll();
	}
}
