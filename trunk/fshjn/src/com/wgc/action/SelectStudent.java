package com.wgc.action;

import java.util.ArrayList;
import java.util.List;

import com.wgc.beans.Student;
import com.wgc.service.StudentService;

public class SelectStudent {
	
	private StudentService stuService;
	private List<Student> stuList = new ArrayList<Student>();
	
	//前台List对象无法传递到后台
	public List<Student> findByXh(List<Student> xhList){
		//前台传递含有学号的学生对象，将此对象根据学号进行查询，取出相应对象，前台取出对象的班级名和姓名。
		for(int i = 0 ;i<xhList.size() ; i++){
			String xh = (xhList.get(i)).getXh();
			this.stuService.findByStuID(xh);
			stuList.add(this.stuService.findByStuID(xh));
		}
		return stuList;
	}

	public void setStuService(StudentService stuService) {
		this.stuService = stuService;
	}
	public StudentService getStuService() {
		return stuService;
	}
}
