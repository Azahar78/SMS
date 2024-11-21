package com.sms.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TeacherRequest {

//    private Integer schoolId;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfJoin;
	private String address;
	private Long mobile;
	private Double salary;
	
}
