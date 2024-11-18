package com.sms.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.School;
import com.sms.payload.SchoolRequest;
import com.sms.payload.SchoolResponse;
import com.sms.repo.SchoolRepo;

@Service
public class SchoolSerivceImpl implements ISchoolService{

	@Autowired
	private SchoolRepo schoolRepo;
	
	@Override
	public boolean enrollSchool(SchoolRequest schoolRequest) {
		
		School school = new School();
		
		BeanUtils.copyProperties(schoolRequest, school);
		
		if(schoolRequest.getEstablishedAt()!=null) {
			LocalDate establishedAt = LocalDate.parse(schoolRequest.getEstablishedAt() + "",
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			school.setEstablishedAt(establishedAt);
			
		}
		
		System.out.println(school);
		
		School schooln = schoolRepo.save(school);
		
        return schooln.getSchoolName()!=null;
	}

	@Override
	public SchoolResponse getSchool(Integer schoolId) throws Exception {
		
		SchoolResponse response = new SchoolResponse();
		
		School school =schoolRepo.findById(schoolId).orElseThrow(()->new Exception("Not Available With id : "+schoolId));
		
		BeanUtils.copyProperties(school,response);
		
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
		
		for(School all:schoolList) {
			
			SchoolResponse schoolResponse = new SchoolResponse();
		 
			BeanUtils.copyProperties(all, schoolResponse);
			
			schoolResonseList.add(schoolResponse);
			
		}
		
	   	
		return schoolResonseList;
	}

	@Override
	public School getSchoolById(Integer schoolId) {
	
		School school=null;
		
	    try {
			
	    	 school = schoolRepo.findById(schoolId)
					.orElseThrow(()->new Exception("Not Available With id : "+schoolId));
			
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return school;
	}

}




/*
 * school.setSchoolName(schoolRequest.getSchoolName());
 * school.setSchoolAddress(schoolRequest.getSchoolAddress());
 * school.setSchoolHours(schoolRequest.getSchoolHours());
 * school.setHolidays(schoolRequest.getHolidays());
 * school.setPhoneNo(schoolRequest.getPhoneNo());
 * school.setEmailId(schoolRequest.getEmailId());
 */