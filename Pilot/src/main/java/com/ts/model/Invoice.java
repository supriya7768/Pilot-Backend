package com.ts.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
	
	@Id
	private Long id;
	private String name;
	private String email;
	private String mobile;
	private String course;
	private int rate;
	private int amount;
	private int subtotal;
	private String dueDate;
	private int dueAmount;
	private int total;

}
