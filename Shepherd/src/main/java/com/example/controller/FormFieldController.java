package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.model.FormField;
import com.example.service.FormFieldService;

@RestController
@CrossOrigin("*")
@RequestMapping("/fields")
public class FormFieldController {

	@Autowired
	private FormFieldService addFieldsService;

	@PostMapping("/create")
	public ResponseEntity<FormField> createFormField(@RequestBody FormField formField) {
		FormField savedFormField = addFieldsService.saveFormField(formField);
		return new ResponseEntity<>(savedFormField, HttpStatus.CREATED);
	}

	@GetMapping("/getlist")
	public ResponseEntity<List<FormField>> getAllFormFields() {
		List<FormField> formFields = addFieldsService.getAllFormFields();
		return new ResponseEntity<>(formFields, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FormField> updateFormField(@PathVariable Long id, @RequestBody FormField formField) {
		FormField updatedFormField = addFieldsService.updateFormField(id, formField);
		return ResponseEntity.ok(updatedFormField);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFormField(@PathVariable Long id) {
		boolean deletionSuccessful = addFieldsService.deleteFormFieldById(id);
		if (deletionSuccessful) {
			return ResponseEntity.ok("Entry deleted successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FormField with ID " + id + " not found.");
		}
	}

}
