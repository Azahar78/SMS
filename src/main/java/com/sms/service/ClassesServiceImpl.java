package com.sms.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Classes;
import com.sms.entity.School;
import com.sms.entity.Teacher;
import com.sms.payload.ClassesRequest;
import com.sms.payload.ClassesResponse;
import com.sms.repo.ClassesRepo;


@Service
public class ClassesServiceImpl implements IClassesService{

	@Autowired
	private ClassesRepo classesRepo;
	
	@Autowired
	private ISchoolService schoolService;
	
	@Autowired
	private ITeacherService teacherService;
	
	@Override
	public Boolean addClasses(ClassesRequest classesRequest){
		
		Classes classes = new Classes();
		
		School school = schoolService.getSchoolById(classesRequest.getSchoolId());
		Teacher teacher = teacherService.getTeacherById(classesRequest.getTeacherID());
		
		BeanUtils.copyProperties(classesRequest, classes);
		
		classes.setSchool(school);
		classes.setTeacher(teacher);
		
		Classes classesSave = classesRepo.save(classes);
		
		return classesSave.getClassName()!=null;
	}

	@Override
	public ClassesResponse getClass(Integer classId) {
		
		ClassesResponse classesResponse = new ClassesResponse();
		
		Classes classes = getClasById(classId);
		
		BeanUtils.copyProperties(classes,classesResponse);
		
		classesResponse.setSchoolId(classes.getSchool().getSchoolId());
		classesResponse.setSchoolName(classes.getSchool().getSchoolName());
		classesResponse.setTeacherId(classes.getTeacher().getTeacherId());
		classesResponse.setTeacherName(classes.getTeacher().getFirstName()+" "+classes.getTeacher().getLastName());
		
		return classesResponse;
	}

	@Override
	public Classes getClasById(Integer classId) {
		
		Classes classes = null;
		
		try {
			
			classes = classesRepo.findById(classId)
					.orElseThrow(()->new Exception("Class not available with that : "+classId));
			
		} catch (Exception e) {
		   e.printStackTrace();
		}
		
		return classes;
	}

}
