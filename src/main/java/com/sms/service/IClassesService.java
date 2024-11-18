package com.sms.service;

import com.sms.entity.Classes;
import com.sms.payload.ClassesRequest;
import com.sms.payload.ClassesResponse;

public interface IClassesService {

	Boolean addClasses(ClassesRequest classesRequest);
	ClassesResponse getClass(Integer classId);
	Classes getClasById(Integer classId);
}
