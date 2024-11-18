package com.sms.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.payload.ClassesRequest;
import com.sms.payload.ClassesResponse;
import com.sms.service.IClassesService;

@RestController
@RequestMapping("/classes")
public class ClassesRestController {

	@Autowired
	private IClassesService classesService;
	
	@PostMapping("/post")
	public ResponseEntity<?> enrollClass(@RequestBody ClassesRequest classesRequest){
		
		Boolean isclasses = classesService.addClasses(classesRequest);
		
		
		return isclasses ? ResponseEntity.ok("Class Added..."):ResponseEntity.ok("Something went Wrong..");
	}
	
	@GetMapping("/fetch/{classId}")
	public ResponseEntity<ClassesResponse> fetchClass(@PathVariable Integer classId){
		return ResponseEntity.ok(classesService.getClass(classId));
	}
}
