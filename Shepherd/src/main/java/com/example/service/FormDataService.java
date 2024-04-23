package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FormDataRepository;
import com.example.model.FormData;

@Service
public class FormDataService {

	@Autowired
    private FormDataRepository formDataRepository;

    public FormData saveFormData(FormData formData) {
        return formDataRepository.save(formData);
    }

}
