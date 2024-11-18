package com.sms.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudentRequest {

	private Integer schoolId;
	private String studentFirstName;
	private String studentLastName;
	private Long mobileNumber;
	private LocalDate dateOfBirth;
	private Long emergencyNumber;
	private String parentEmail;
	private String studentAddress;

}
