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
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teacherId;
//	private Boolean isAvailable;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfJoin;
	private String address;
	private Long mobile;
	private Double salary;
	
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;
	
	@OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Classes> classes;
}
