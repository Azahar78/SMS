package com.sms.service;

import java.util.List;

import com.sms.entity.School;
import com.sms.payload.SchoolRequest;
import com.sms.payload.SchoolResponse;

public interface ISchoolService {

	boolean enrollSchool(SchoolRequest schoolRequest);
	
	SchoolResponse getSchool(Integer schoolId) throws Exception;
	
	List<SchoolResponse> getAllSchool();
	
	School getSchoolById(Integer schoolId);
}
