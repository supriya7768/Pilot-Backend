package com.ts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.Invoice;
import com.ts.service.InvoiceService;

@CrossOrigin("*")
@RestController
public class InvoiceController {
	
	@Autowired
	InvoiceService is;

	@PostMapping("/add-invoice")
	public Invoice addInvoice(@RequestBody Invoice invoice) {
		return is.addInvoice(invoice);

	}
	

}
