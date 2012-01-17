package com.wgc.action;

import com.wgc.beans.Appoint;
import com.wgc.service.AppointService;

//更新预约审核状态，由管理员来操作
public class UpdateAppoint {

	private AppointService appoService;

	public Boolean updateShzt(String yyID,Boolean shzt){

		try{
				Appoint appo = this.appoService.findById(Integer.parseInt(yyID));
				this.appoService.deleteRecord(appo);
				appo.setShzt(true);
				this.appoService.saveRecord(appo);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	
	public AppointService getAppoService() {
		return appoService;
	}
	public void setAppoService(AppointService appoService) {
		this.appoService = appoService;
	}
}
