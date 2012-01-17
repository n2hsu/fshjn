package com.wgc.service;

import java.util.List;

import com.wgc.beans.Student;
import com.wgc.dao.StudentDAO;

public class StudentService {

	private StudentDAO stuDao;
	public Student findByStuID(String id){
		
		return this.stuDao.findById(id);
	}
	public List<Student> findByBjm(String bjm){
		
		return (List<Student>)this.stuDao.findByBjm(bjm);
		
	}
	public StudentDAO getStuDao() {
		return stuDao;
	}
	public void setStuDao(StudentDAO stuDao) {
		this.stuDao = stuDao;
	}
	
}
