package com.wgc.action;

import java.util.List;

import com.wgc.beans.Senser;
import com.wgc.service.SenserService;

public class SelectSenser {

	private SenserService senserService;

	public List<Senser> findByLxMc(String mc,int lx){
		
		String mcTemp = null;
		try{

			mcTemp = mc.trim().substring(0,4);
		}catch(Exception e){
			e.printStackTrace();
		}
		return this.senserService.findByLxMc(mcTemp, lx);
	}
	public List<Senser> findByLxMc1(String mc,int lx){
		
		String mcTemp1 = null;
		try{

			mcTemp1 = mc.trim().substring(0,4);
		}catch(Exception e){
			e.printStackTrace();
		}
		return this.senserService.findByLxMc1(mcTemp1, lx);
	}
	
	public List<Senser> findByLm(String lm){
		
		
		String str = null;
		if(lm.trim().equals("信息楼")){
			str = "1";
		}if(lm.trim().equals("材料楼")){
			str = "2";
		}if(lm.trim().equals("文学楼")){
			str = "3";
		}if(lm.trim().equals("外语楼")){
			str = "4";
		}if(lm.trim().equals("经济楼")){
			str = "5";
		}
		return this.senserService.findByLh(str);
	}
	
	public SenserService getSenserService() {
		return senserService;
	}

	public void setSenserService(SenserService senserService) {
		this.senserService = senserService;
	}

}
