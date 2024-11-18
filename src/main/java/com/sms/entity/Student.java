package com.sms.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;
	private Long emergencyNumber;
	private Long mobileNumber;
	private LocalDate dateOfBirth;
	private String parentEmail;
	private String studentAddress;
	private String studentFirstName;
	private String studentLastName;

	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;
}
