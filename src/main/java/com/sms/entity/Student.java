package com.sms.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Grade> grades;
}
