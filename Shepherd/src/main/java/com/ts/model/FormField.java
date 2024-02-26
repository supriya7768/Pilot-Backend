package com.ts.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class FormField {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String label; // Field label (e.g., "Full Name")
	private String type; // Field type (e.g., "text", "dropdown", "radio")

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> options; // Field options for dropdown or radio buttons (array)

}
