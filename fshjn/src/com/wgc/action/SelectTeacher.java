package com.wgc.action;

import com.wgc.service.TeacherService;

public class SelectTeacher {

	private TeacherService teacherService;
	
	public Boolean checkTeacher(String jgh,String xm){
		
		if(this.teacherService.findById(jgh).getXm().trim().equals(xm.trim())){		
			return true;
		}else{
			return false;
		}
	}
	
	public TeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
}
