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
		if(lm.trim().equals("��Ϣ¥")){
			str = "1";
		}if(lm.trim().equals("����¥")){
			str = "2";
		}if(lm.trim().equals("��ѧ¥")){
			str = "3";
		}if(lm.trim().equals("����¥")){
			str = "4";
		}if(lm.trim().equals("����¥")){
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
