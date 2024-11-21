package com.sms.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.School;
import com.sms.entity.Student;
import com.sms.payload.SchoolResponse;
import com.sms.payload.StudentRequest;
import com.sms.payload.StudentResponse;
import com.sms.repo.SchoolRepo;
import com.sms.repo.StudentRepo;

@Service
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private SchoolRepo schoolRepo;
	
	
	
	@Override
	public Boolean registerStudent(StudentRequest studentRequest) throws Exception {
		
		School school = schoolRepo.findById(studentRequest.getSchoolId())
				.orElseThrow(() -> new Exception(" School not found with " + studentRequest.getSchoolId()));
       
		Student student = new Student();
		
		BeanUtils.copyProperties(studentRequest, student);
		
		if(studentRequest.getDateOfBirth()!=null) {
			LocalDate dob = LocalDate.parse(studentRequest.getDateOfBirth() + "",
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			student.setDateOfBirth(dob);
			
		}
		
		student.setSchool(school);
		
		Student studentSave = studentRepo.save(student);
		
		return studentSave.getStudentFirstName()!=null;
	}

	@Override
	public StudentResponse getStudent(Integer StudentId) throws Exception {
		
		Student student = studentRepo.findById(StudentId)
		.orElseThrow(() -> new Exception(" Student not found with " + StudentId));
	       
		StudentResponse studentResponse = new StudentResponse();
		
		BeanUtils.copyProperties(student, studentResponse);
		
		studentResponse.setSchoolId(student.getSchool().getSchoolId());
		studentResponse.setSchoolName(student.getSchool().getSchoolName());
		
		return studentResponse;
	}

	@Override
	public Student getStudentById(Integer studentId) {
		
		Student student =new Student();
		
		try {
			
			student=studentRepo.findById(studentId)
			.orElseThrow(()->new Exception("Student not found with : "+studentId));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

}
