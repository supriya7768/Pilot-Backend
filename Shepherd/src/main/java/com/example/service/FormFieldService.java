package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FormFieldRepository;
import com.example.model.FormField;

@Service
public class FormFieldService {

	@Autowired
	private FormFieldRepository formFieldRepository;

	public FormField saveFormField(FormField formField) {
		return formFieldRepository.save(formField);
	}

	public List<FormField> getAllFormFields() {
		return formFieldRepository.findAll();
	}

	public boolean deleteFormFieldById(Long id) {
		Optional<FormField> formFieldOptional = formFieldRepository.findById(id);
		if (formFieldOptional.isPresent()) {
			formFieldRepository.deleteById(id);
			return true; // Deletion successful
		} else {
			return false; // FormField with the given ID not found
		}
	}

	public FormField updateFormField(Long id, FormField updatedFormField) {
		Optional<FormField> existingFormFieldOptional = formFieldRepository.findById(id);

		if (existingFormFieldOptional.isPresent()) {
			FormField existingFormField = existingFormFieldOptional.get();
			// Update existing form field with the new values
			existingFormField.setLabel(updatedFormField.getLabel());
			existingFormField.setType(updatedFormField.getType());
			existingFormField.setOptions(updatedFormField.getOptions());

			// Save the updated form field
			return formFieldRepository.save(existingFormField);
		} else {
			throw new IllegalArgumentException("FormField with ID " + id + " not found");
		}
	}

}
