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

import com.sms.payload.SchoolRequest;
import com.sms.payload.SchoolResponse;
import com.sms.service.ISchoolService;

@RestController
@RequestMapping("/school")
public class SchoolRestController {

	@Autowired
	private ISchoolService schoolService; 
	
	@PostMapping("/post")
	public ResponseEntity<?> registerSchool(@RequestBody SchoolRequest request){
		
		boolean isSchoolEnroll = schoolService.enrollSchool(request);
		
		return isSchoolEnroll ? ResponseEntity.ok(" School Registration Done"): ResponseEntity.ok("Somthing Went Wrong"); 
	}
	
	@GetMapping("/fetch/{schoolId}")
	public ResponseEntity<?> fetchSchool(@PathVariable Integer schoolId){
		
		ResponseEntity response = null;
		
		try {
			SchoolResponse school = schoolService.getSchool(schoolId);
			
			response = ResponseEntity.ok(school);
			
		} catch (Exception e) {
			e.printStackTrace();
			response=ResponseEntity.internalServerError().body("Somthing went Wrong..");
		}
		return response;
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<List<SchoolResponse>> fetchAllSchool(){
		
		List<SchoolResponse> allSchool = schoolService.getAllSchool();
		
		return ResponseEntity.ok(allSchool);
	}
	
}
