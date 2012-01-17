package com.wgc.service;

import java.util.List;

import com.wgc.beans.Senser;
import com.wgc.dao.SenserDAO;

public class SenserService {

	private SenserDAO senserDao;
	
	public List<Senser> findByLxMc(String cgqmc,int lxbh){
	
		return this.senserDao.findByLxBh(cgqmc, lxbh);
		
	}
	public List<Senser> findByLxMc1(String cgqmc,int lxbh){
		
		return this.senserDao.findByLxBh1(cgqmc, lxbh);
		
	}
	public List<Senser> find(String hql){
		
		return this.senserDao.find(hql);
	} 
	public List<Senser> findByLh(String lh){
		
		return this.senserDao.findByLm(lh);
	}

	public SenserDAO getSenserDao() {
		return senserDao;
	}
	public void setSenserDao(SenserDAO senserDao) {
		this.senserDao = senserDao;
	}
}
