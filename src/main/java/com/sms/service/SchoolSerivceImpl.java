package com.sms.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Classes;
import com.sms.entity.School;
import com.sms.entity.Student;
import com.sms.entity.Teacher;
import com.sms.payload.ClassesResponse;
import com.sms.payload.SchoolRequest;
import com.sms.payload.SchoolResponse;
import com.sms.payload.StudentResponse;
import com.sms.payload.TeacherResponse;
import com.sms.repo.SchoolRepo;

@Service
public class SchoolSerivceImpl implements ISchoolService {

	@Autowired
	private SchoolRepo schoolRepo;

	@Override
	public SchoolResponse enrollSchool(SchoolRequest schoolRequest) {

		School school = new School();

		BeanUtils.copyProperties(schoolRequest, school);

		if (schoolRequest.getEstablishedAt() != null) {
			LocalDate establishedAt = LocalDate.parse(schoolRequest.getEstablishedAt() + "",
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			school.setEstablishedAt(establishedAt);

		}

		System.out.println(school);

		School schooln = schoolRepo.save(school);

		SchoolResponse schoolResponse = new SchoolResponse();

		BeanUtils.copyProperties(schooln, schoolResponse);

		return schoolResponse;
	}

	@Override
	public SchoolResponse getSchool(Integer schoolId) throws Exception {

		SchoolResponse response = new SchoolResponse();

		School school = schoolRepo.findById(schoolId)
				.orElseThrow(() -> new Exception("Not Available With id : " + schoolId));

		BeanUtils.copyProperties(school, response);

		return response;
	}

	@Override
	public List<SchoolResponse> getAllSchool() {
		// TODO Auto-generated method stub

		List<School> schoolList = schoolRepo.findAll();

		List<SchoolResponse> schoolResonseList = new ArrayList<>();

		/*
		 * for(School sch:schoolList) {
		 * 
		 * BeanUtils.copyProperties(schoolList, schoolResonseList); }
		 */

		for (School all : schoolList) {

			SchoolResponse schoolResponse = new SchoolResponse();

			BeanUtils.copyProperties(all, schoolResponse);

			schoolResonseList.add(schoolResponse);

		}

		return schoolResonseList;
	}

	@Override
	public School getSchoolById(Integer schoolId) {

		School school = null;

		try {

			school = schoolRepo.findById(schoolId)
					.orElseThrow(() -> new Exception("Not Available With id : " + schoolId));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return school;
	}

	@Override
	public List<TeacherResponse> allteacherBySchoolId(Integer schoolId) {
		List<Teacher> teacher = schoolRepo.findTeachersBySchoolId(schoolId);

		List<TeacherResponse> teacherResponses = new ArrayList<>();

		for (Teacher teacher2 : teacher) {

			TeacherResponse teacherResponse = new TeacherResponse();

			BeanUtils.copyProperties(teacher2, teacherResponse);

			teacherResponse.setSchoolId(teacher2.getSchool().getSchoolId());
			teacherResponse.setSchoolName(teacher2.getSchool().getSchoolName());

			teacherResponses.add(teacherResponse);

		}

		return teacherResponses;
	}

	@Override
	public List<StudentResponse> allStudentsBySchoolId(Integer schoolId) {

		List<Student> students = schoolRepo.findStudentBySchoolId(schoolId);

		List<StudentResponse> studentResponses = new ArrayList<>();

		students.forEach(student -> {

			StudentResponse studentResponse = new StudentResponse();

			BeanUtils.copyProperties(student, studentResponse);

			studentResponse.setSchoolId(student.getSchool().getSchoolId());
			studentResponse.setSchoolName(student.getSchool().getSchoolName());

			studentResponses.add(studentResponse);

		});

		return studentResponses;
	}

	@Override
	public List<ClassesResponse> alltClassesBySchoolId(Integer schoolId) {

		List<Classes> classes = schoolRepo.findClassesBySchoolId(schoolId);

		List<ClassesResponse> classesResponse = new ArrayList<>();

		classes.forEach(cls -> {

			ClassesResponse clsResponse = new ClassesResponse();

			BeanUtils.copyProperties(cls, clsResponse);
			
			clsResponse.setSchoolId(cls.getSchool().getSchoolId());
			clsResponse.setSchoolName(cls.getSchool().getSchoolName());
			clsResponse.setTeacherId(cls.getTeacher().getTeacherId());
			clsResponse.setTeacherName(cls.getTeacher().getFirstName()+" "+cls.getTeacher().getFirstName());
		    
		    classesResponse.add(clsResponse);

		});
		return classesResponse;
	}

	/*
	 * @Override public List<Object> searchSchoolResult(Integer schoolId, String
	 * type) {
	 * 
	 * List<Object> responseObject =null;
	 * 
	 * switch (type) {
	 * 
	 * case "student": {
	 * 
	 * 
	 * 
	 * responseObject = new ArrayList<>();
	 * 
	 * break; }
	 * 
	 * case "teacher": {
	 * 
	 * List<TeacherResponse> teacherResponse = allteacherBySchoolId(schoolId);
	 * 
	 * responseObject = new ArrayList<>(teacherResponse); break; }
	 * 
	 * case "classes":{
	 * 
	 * }
	 * 
	 * default: throw new IllegalArgumentException("Unexpected value: " + type); }
	 * 
	 * return responseObject; }
	 * 
	 */
}

/*
 * school.setSchoolName(schoolRequest.getSchoolName());
 * school.setSchoolAddress(schoolRequest.getSchoolAddress());
 * school.setSchoolHours(schoolRequest.getSchoolHours());
 * school.setHolidays(schoolRequest.getHolidays());
 * school.setPhoneNo(schoolRequest.getPhoneNo());
 * school.setEmailId(schoolRequest.getEmailId());
 */