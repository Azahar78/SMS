package com.sms.rest;

import java.util.List;

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

	@PostMapping("/post/{schoolId}")
	public ResponseEntity<?> enrollTeacher(@RequestBody TeacherRequest teacherRequest,@PathVariable Integer schoolId ) {

		ResponseEntity response = null;

		try {
			 TeacherResponse teacherResponse = teacherService.registerTeacher(teacherRequest,schoolId);

			response = ResponseEntity.ok(teacherResponse);
					

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
	
	@GetMapping("/fetchAll")
	public ResponseEntity<List<TeacherResponse>> fetchAllTeacher(){
		List<TeacherResponse> allTeachers = teacherService.getAllTeachers();
		return ResponseEntity.ok(allTeachers);
	}
}
