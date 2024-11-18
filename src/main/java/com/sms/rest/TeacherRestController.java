package com.sms.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.payload.TeacherRequest;
import com.sms.payload.TeacherResponse;
import com.sms.service.ITeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherRestController {

	@Autowired
	private ITeacherService teacherService;

	@PostMapping("/post")
	public ResponseEntity<?> enrollTeacher(@RequestBody TeacherRequest teacherRequest) {

		ResponseEntity response = null;

		try {
			Boolean isRegisterTeacher = teacherService.registerTeacher(teacherRequest);

			response = isRegisterTeacher ? ResponseEntity.ok("Teacher Registration Done..")
					: ResponseEntity.internalServerError().body(" Something went wrong");

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Internal server Error ");
			response = ResponseEntity.internalServerError().body(" Something went wrong from Cach");

		}

		return response;
	}
    
	@GetMapping("/fetch/{teacherId}")
	public ResponseEntity<TeacherResponse> fetchTeacher(@PathVariable Integer teacherId ){
	
		ResponseEntity response =null;
		
		try {
			TeacherResponse teacherResponse = teacherService.getTeacher(teacherId);
			
		   response = ResponseEntity.ok(teacherResponse);	
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = ResponseEntity.internalServerError().body("Somthing went Wrong : "+e.getMessage());
		}
		
		return response;
	}
}
