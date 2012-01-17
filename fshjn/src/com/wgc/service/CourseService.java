package com.wgc.service;

import com.wgc.beans.Course;
import com.wgc.dao.CourseDAO;

public class CourseService {

	private CourseDAO courseDao;
	public Course findById(int id){
		
		return this.courseDao.findById(id);
	}

	
	public CourseDAO getCourseDao() {
		return courseDao;
	}
	public void setCourseDao(CourseDAO courseDao) {
		this.courseDao = courseDao;
	}
}
