package com.sms.service;

import com.sms.entity.Student;
import com.sms.payload.StudentRequest;
import com.sms.payload.StudentResponse;

public interface IStudentService {

	Boolean registerStudent(StudentRequest studentRequest) throws Exception;

	StudentResponse getStudent(Integer StudentId) throws Exception;
	
	Student getStudentById(Integer studentId);
}
