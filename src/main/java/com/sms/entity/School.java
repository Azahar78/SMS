package com.sms.entity;

import java.time.LocalDate;
import java.util.List;

import org.aspectj.weaver.tools.Trace;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity

public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer schoolId;
	private String schoolName;
	private String SchoolAddress;
	private String schoolHours;
	private String holidays;
	private Long phoneNo;
	private String emailId;

	
	private LocalDate establishedAt;

	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Teacher> teachers;
	
	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> student;
	
	@OneToMany(mappedBy = "school",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Classes> classes;
	
}
