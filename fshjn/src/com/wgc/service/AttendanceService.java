package com.wgc.service;

import java.util.List;

import com.wgc.beans.Attendance;
import com.wgc.dao.AttendanceDAO;

public class AttendanceService {
	
	private AttendanceDAO attendDao;
	public List<Attendance> findByKch(String kch){
		try{
			return this.attendDao.findByKch(kch);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Attendance> findBySkrq(String skrq){
		
		return this.attendDao.findBySkrq(skrq);
	}

	public AttendanceDAO getAttendDao() {
		return attendDao;
	}
	public void setAttendDao(AttendanceDAO attendDao) {
		this.attendDao = attendDao;
	}

}
