package com.wgc.service;

import java.sql.Timestamp;
import java.util.List;

import com.wgc.dao.AppointDAO;
import com.wgc.beans.Appoint;

public class AppointService {
	
	private AppointDAO appointDao;
	
	public void saveRecord(Appoint appo){
		
		this.appointDao.save(appo);
	}
	public void deleteRecord(Appoint appo){
		this.appointDao.delete(appo);
	}
	public Appoint findById(int id){
		
		return this.appointDao.findById(id);
	}
	public List<Appoint> findAll(){
		
		return this.appointDao.findAll();
	}
	public List<Appoint> findByShzt(Boolean shzt){
		
		return this.appointDao.findByShzt(shzt);
	}
	public List<Appoint> findByYyjs(String yyjs){

		return this.appointDao.findByYyjs(yyjs);
	}
	public List<Appoint> findByXhYysj(String xh,Timestamp yysj){
		
		String hql = "from Appoint appo where appo.stu.xh='"+xh+"' and appo.yysj='"+yysj+"'";
		try{
			return this.appointDao.find(hql);
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
	}

	public AppointDAO getAppointDao() {
		return appointDao;
	}

	public void setAppointDao(AppointDAO appointDao) {
		this.appointDao = appointDao;
	}
}
