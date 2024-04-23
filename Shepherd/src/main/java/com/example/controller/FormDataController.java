package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.FormData;
import com.example.service.FormDataService;

@RestController
@CrossOrigin("*")
public class FormDataController {

	@Autowired
	private FormDataService formDataService;

	@PostMapping("/create")
	public ResponseEntity<FormData> createForm(@RequestBody FormData formData) {
		FormData savedFormData = formDataService.saveFormData(formData);
		return new ResponseEntity<>(savedFormData, HttpStatus.CREATED);
	}

}
