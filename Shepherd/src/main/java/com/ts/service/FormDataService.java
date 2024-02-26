package com.ts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.dao.FormDataRepository;
import com.ts.model.FormData;

@Service
public class FormDataService {

	@Autowired
    private FormDataRepository formDataRepository;

    public FormData saveFormData(FormData formData) {
        return formDataRepository.save(formData);
    }

}
