package com.sms.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.School;
import com.sms.entity.Teacher;
import com.sms.payload.TeacherRequest;
import com.sms.payload.TeacherResponse;
import com.sms.repo.SchoolRepo;
import com.sms.repo.TeacherRepo;

@Service
public class TeacherServiceImpl implements ITeacherService {

	@Autowired
	private TeacherRepo teacherRepo;

	@Autowired
	private SchoolRepo schoolRepo;

	@Override
	public TeacherResponse registerTeacher(TeacherRequest teacherRequest, Integer schoolId) throws Exception {

		School school = schoolRepo.findById(schoolId)
				.orElseThrow(() -> new Exception(" School not found with " + schoolId));

		Teacher teacher = new Teacher();

		BeanUtils.copyProperties(teacherRequest, teacher);

		if (teacherRequest.getDateOfJoin() != null) {
			LocalDate doj = LocalDate.parse(teacherRequest.getDateOfJoin() + "",
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			teacher.setDateOfJoin(doj);

		}

		teacher.setSchool(school);

		Teacher teacherSave = teacherRepo.save(teacher);

		TeacherResponse teacherResponse = new TeacherResponse();

		BeanUtils.copyProperties(teacherSave, teacherResponse);

		teacherResponse.setSchoolId(teacherSave.getSchool().getSchoolId());

		teacherResponse.setSchoolName(teacherSave.getSchool().getSchoolName());
		
	

		return teacherResponse;
	}

	@Override
	public TeacherResponse getTeacher(Integer teacherId) throws Exception {

		Teacher teacher = teacherRepo.findById(teacherId)
				.orElseThrow(() -> new Exception(" Teacher Not Available With " + teacherId));

		TeacherResponse teacherResponse = new TeacherResponse();

		BeanUtils.copyProperties(teacher, teacherResponse);

		teacherResponse.setSchoolId(teacher.getSchool().getSchoolId());

		teacherResponse.setSchoolName(teacher.getSchool().getSchoolName());

		return teacherResponse;
	}

	@Override
	public List<TeacherResponse> getAllTeachers() {
		
		List<Teacher> allTeacher = teacherRepo.findAll();

		List<TeacherResponse>teacherResponses = new ArrayList<>();
		
		for(Teacher teachers :allTeacher) {
			
			TeacherResponse response = new TeacherResponse();
			
			BeanUtils.copyProperties(teachers, response);
			
			response.setSchoolId(teachers.getSchool().getSchoolId());

			response.setSchoolName(teachers.getSchool().getSchoolName());
			
		    teacherResponses.add(response);
		}
		
		return teacherResponses;
	}

	@Override
	public Teacher getTeacherById(Integer teacherId) {

		Teacher teacher = null;

		try {

			teacher = teacherRepo.findById(teacherId)
					.orElseThrow(() -> new Exception(" Teacher Not Available With " + teacherId));

		} catch (Exception e) {

			e.printStackTrace();
		}

		return teacher;
	}

}
