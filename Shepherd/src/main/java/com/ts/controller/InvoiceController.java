package com.ts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.Invoice;
import com.ts.service.InvoiceService;

@CrossOrigin("*")
@RestController
public class InvoiceController {

	@Autowired
	InvoiceService is;

	@PostMapping("/add-invoice")
	public String addInvoice(@RequestBody Invoice invoice) {
		return is.addInvoice(invoice);
	}

	@GetMapping("/get-invoice-data")
	@ResponseBody
	public List<Invoice> getInvoiceData() {
		List<Invoice> invoice = is.getAllInvoiceData();
		return invoice;
	}
	
	@GetMapping("/last-invoice-id")
    public int getLastInvoiceId() {
        return is.getLastInvoiceId();
    }
}
