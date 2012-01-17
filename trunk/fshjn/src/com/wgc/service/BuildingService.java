package com.wgc.service;

import java.util.List;

import com.wgc.beans.Building;
import com.wgc.dao.BuildingDAO;

public class BuildingService {

	private BuildingDAO bInfoDao ;
	
	@SuppressWarnings("unchecked")
	public List<Building> findAllBuilding(){
		
		return this.bInfoDao.findAll();
		
	}
	public List<Building> findCarPark(String lm){
		
		return this.bInfoDao.findByLm(lm);
		
	}
	public BuildingDAO getbInfoDao() {
		return bInfoDao;
	}

	public void setbInfoDao(BuildingDAO bInfoDao) {
		this.bInfoDao = bInfoDao;
	}
}
