package com.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.entity.Grade;

public interface GradeRepo extends JpaRepository<Grade, Integer>{

}
