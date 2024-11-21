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

import com.sms.payload.ClassesResponse;
import com.sms.payload.SchoolRequest;
import com.sms.payload.SchoolResponse;
import com.sms.payload.StudentResponse;
import com.sms.payload.TeacherResponse;
import com.sms.service.ISchoolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/school")
public class SchoolRestController {

	@Autowired
	private ISchoolService schoolService;

	/*
	 * @Parameter(description = "Staff status ", required = true, schema
	 * = @Schema(allowableValues = { "ONLINE", "OFFLINE" })) @PathVariable String
	 * status
	 * 
	 */

	@Operation(summary = "Create a new school", description = "Add a school with its details")
	@PostMapping("/post")
	public ResponseEntity<?> registerSchool(@RequestBody SchoolRequest request) {

		ResponseEntity response = null;

		SchoolResponse schoolResponse = schoolService.enrollSchool(request);

		if (schoolResponse != null) {
			response = ResponseEntity.ok(schoolResponse);
		} else {
			response = ResponseEntity.internalServerError().body("Something went Wrong");
		}

		return response;

	}

	@GetMapping("/fetch/{schoolId}")
	public ResponseEntity<?> fetchSchool(@PathVariable Integer schoolId) {

		ResponseEntity response = null;

		try {
			SchoolResponse school = schoolService.getSchool(schoolId);

			response = ResponseEntity.ok(school);

		} catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.internalServerError().body("Somthing went Wrong..");
		}
		return response;
	}

	@GetMapping("/fetchAll")
	public ResponseEntity<List<SchoolResponse>> fetchAllSchool() {

		List<SchoolResponse> allSchool = schoolService.getAllSchool();

		return ResponseEntity.ok(allSchool);
	}

	@GetMapping("/search/{schoolId}/{type}")
	public ResponseEntity<?> search(@PathVariable Integer schoolId,
			@Parameter(description = "School search", required = true, schema = @Schema(allowableValues = { "student",
			"teacher", "classes" })) @PathVariable String type) {

		ResponseEntity response = null;
		
		switch (type) {

		case "student": {
			List<StudentResponse> studentResponses = schoolService.allStudentsBySchoolId(schoolId);
			response = ResponseEntity.ok(studentResponses);
			break;
		}

		case "teacher": {
			List<TeacherResponse> teacherResponses = schoolService.allteacherBySchoolId(schoolId);
			response = ResponseEntity.ok(teacherResponses);
			break;
		}

		case "classes": {

			List<ClassesResponse> classesResponse = schoolService.alltClassesBySchoolId(schoolId);
			response =ResponseEntity.ok(classesResponse);
			break;
		}

		default:
			
			throw new IllegalArgumentException("Unexpected value: " + type);
			
		}

		return response;
	}

}
