package com.wgc.service;

import com.wgc.beans.Teacher;
import com.wgc.dao.TeacherDAO;

public class TeacherService {

	private TeacherDAO teacherDao;
	public Teacher  findById(String jgh){
		
		return this.teacherDao.findById(jgh);
	}
	public TeacherDAO getTeacherDao() {
		return teacherDao;
	}
	public void setTeacherDao(TeacherDAO teacherDao) {
		this.teacherDao = teacherDao;
	}
}
