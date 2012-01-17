package com.wgc.action;

import java.sql.Timestamp;
import java.util.List;

import com.wgc.beans.Appoint;
import com.wgc.beans.Student;
import com.wgc.service.AppointService;
import com.wgc.service.StudentService;

public class SelectAppoint {

	private AppointService appoService;
	private StudentService stuService;
	public List<Appoint> findAllAppo(){
		
		return this.appoService.findAll();
	}
	//根据审核状态查询
	public List<Appoint> findByShzt(String shzt){
		if(shzt.trim().equals("false")){
			return this.appoService.findByShzt(false);	
		}if(shzt.trim().equals("all")){
				return this.appoService.findAll();
		}else{
			return null;
		}
		
	}
	//根据学号查询
	public List<Appoint> findByXhYysj(String xh,String xm,String yysj){
			
		Student stu = this.stuService.findByStuID(xh);
		if(!stu.getXm().trim().equals(xm)){
			return null;
		}else{
			try{
				yysj = yysj+" 00:00:00";
				Timestamp time = Timestamp.valueOf(yysj);
				
				return this.appoService.findByXhYysj(xh,time);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public AppointService getAppoService() {
		return appoService;
	}
	public void setAppoService(AppointService appoService) {
		this.appoService = appoService;
	}
	public StudentService getStuService() {
		return stuService;
	}
	public void setStuService(StudentService stuService) {
		this.stuService = stuService;
	}
	
}
