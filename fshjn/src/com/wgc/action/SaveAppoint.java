package com.wgc.action;

import java.sql.Timestamp;

import com.wgc.beans.Appoint;
import com.wgc.service.AppointService;
import com.wgc.service.StudentService;

public class SaveAppoint {
	
	private AppointService appoService;
	private StudentService stuService;
	private Appoint appo;
	
	
	//�����ύ��ԤԼ����
	public Boolean saveRecord(String xm,String xh,String jsh,String jc,String yysj,String lxfs){
		//δ�ж��Ƿ���γ̱��г�ͻ			
		if(this.stuService.findByStuID(xh).getXm().trim().equals(xm.trim())){
			
			appo = new Appoint();
			try{
				Short jcShort = Short.parseShort(jc);
				Timestamp yysjT = Timestamp.valueOf(yysj);

				appo.setJs(jcShort);
				appo.setStu(this.stuService.findByStuID(xh));
				appo.setYyjs(jsh);
				appo.setYysj(yysjT);
				appo.setLxfs(lxfs);
				appo.setShzt(false);
				this.appoService.saveRecord(appo);
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}else{
			return false;	
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
