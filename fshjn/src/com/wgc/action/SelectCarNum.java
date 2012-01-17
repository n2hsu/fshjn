package com.wgc.action;

import java.util.List;

import com.wgc.beans.Building;
import com.wgc.service.BuildingService;

public class SelectCarNum {

	private BuildingService buildingService;
	
	public List<Building> findCarNum(String lm){
		
		String temp = lm.substring(lm.length()-3, lm.length());
		if(temp.equals("停车场")){
			temp = "信息楼";		
		}
		return this.buildingService.findCarPark(temp);
	}
	public List<Building> findCarAllNum(){
	
		return this.buildingService.findAllBuilding();
	}

	public BuildingService getBuildingService() {
		return buildingService;
	}

	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

}
