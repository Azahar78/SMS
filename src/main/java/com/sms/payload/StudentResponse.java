package com.sms.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudentResponse {

	private Integer schoolId;
	private String schoolName;
	private String studentFirstName;
	private String studentLastName;
	private Long mobileNumber;
	private LocalDate dateOfBirth;
	private Long emergencyNumber;
	private String parentEmail;
	private String studentAddress;
	
}
