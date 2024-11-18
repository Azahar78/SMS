package com.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.entity.School;

public interface SchoolRepo extends JpaRepository<School, Integer>{

}
