package com.sms.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.payload.StudentRequest;
import com.sms.payload.StudentResponse;
import com.sms.service.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentRestController {

	@Autowired
	private IStudentService studentService;

	@PostMapping("/post")
	public ResponseEntity<?> enrollStudent(@RequestBody StudentRequest studentRequest) {

		ResponseEntity response = null;

		try {

			Boolean isRegisterStudent = studentService.registerStudent(studentRequest);

			response = isRegisterStudent ? ResponseEntity.ok("Student Registration Done..")
					: ResponseEntity.internalServerError().body("Something went Wrong");

		} catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.internalServerError().body("Something went Wrong from catch..");
		}

		return response;
	}

	@GetMapping("/fetch/{studentId}")
	public ResponseEntity<StudentResponse> fetchStudent(@PathVariable Integer studentId){
		
		StudentResponse studentResponse =null;
		
		try {
			 studentResponse = studentService.getStudent(studentId);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(studentResponse);
	}
}
