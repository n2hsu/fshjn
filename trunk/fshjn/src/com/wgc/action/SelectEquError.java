package com.wgc.action;

import java.util.List;

import com.wgc.beans.Senser;
import com.wgc.service.SenserService;

public class SelectEquError {

	private SenserService senserService;
	
	public List<Senser> findEquError(String equipName,String tOrF){
		
		String hql  = "null";
		int cgqlmID = 0;
		Short sfsh = 0;
		//判断设备
		if(equipName.toString() == null){
			cgqlmID = 0;
		}else{
			if(equipName.toString().equals("电脑")){
				cgqlmID =1;
			}else{
				if(equipName.toString().equals("投影仪")){
					cgqlmID = 2;
				}else{
					if(equipName.toString().equals("门禁")){
						cgqlmID = 6;
					}else{
						if(equipName.toString().equals("荧光灯")){
							cgqlmID = 12;
						}else{
							cgqlmID = 0;
						}
					}
				}
			}
		}
		//已损坏,情况良好,默认已损坏
		if(tOrF.trim().substring(0, 1).equals("1")){
			sfsh = 0;
		}else{
			sfsh = 1;
		}				
		if(cgqlmID==0){
			hql = "from Senser sens where sfsh="+sfsh;
		}else{
			hql = "from Senser sens where sfsh="+sfsh+" and ss.cgqlxID='"+cgqlmID+"'";
		}	
		return this.senserService.find(hql);
	}
	
	public SenserService getSenserService() {
		return senserService;
	}

	public void setSenserService(SenserService senserService) {
		this.senserService = senserService;
	}
	
}
