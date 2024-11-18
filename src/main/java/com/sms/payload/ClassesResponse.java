package com.sms.payload;

import lombok.Data;

@Data
public class ClassesResponse {

	private Integer classId;
	private String className;
    private Integer schoolId;
    private String schoolName;
    private Integer teacherId;
    private String  teacherName;
}
