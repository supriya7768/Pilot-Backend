package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long invoiceId;
	private String name;
	private String email;
	private String mobile;
	private String course;
	private int rate;
//	private int amount;
	private double subtotal;
	private String paidAmount;
	private String dueDate;
	private double dueAmount;
	private double total;

	@ManyToOne // Many invoices can be related to one LeadForm
	private LeadForm leadForm;

}
