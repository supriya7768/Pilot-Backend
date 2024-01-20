package com.ts.model;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LeadForm getLeadForm() {
		return leadForm;
	}

	public void setLeadForm(LeadForm leadForm) {
		this.leadForm = leadForm;
	}

	public Invoice(Long id, long invoiceId, String name, String email, String mobile, String course, int rate,
			double subtotal, String paidAmount, String dueDate, double dueAmount, double total, LeadForm leadForm) {
		super();
		this.id = id;
		this.invoiceId = invoiceId;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.course = course;
		this.rate = rate;
		this.subtotal = subtotal;
		this.paidAmount = paidAmount;
		this.dueDate = dueDate;
		this.dueAmount = dueAmount;
		this.total = total;
		this.leadForm = leadForm;
	}
	
	public Invoice() {
		// TODO Auto-generated constructor stub
	}

}
