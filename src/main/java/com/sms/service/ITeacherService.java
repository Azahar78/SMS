package com.sms.service;

import java.util.List;

import com.sms.entity.Teacher;
import com.sms.payload.TeacherRequest;
import com.sms.payload.TeacherResponse;

public interface ITeacherService {

	TeacherResponse registerTeacher(TeacherRequest teacherRequest, Integer schoolId) throws Exception;
	TeacherResponse getTeacher(Integer teacherId)throws Exception;
	Teacher getTeacherById(Integer teacherId);
	List<TeacherResponse> getAllTeachers();
}
