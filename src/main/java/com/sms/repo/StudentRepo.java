package com.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

}
