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
		//�ж��豸
		if(equipName.toString() == null){
			cgqlmID = 0;
		}else{
			if(equipName.toString().equals("����")){
				cgqlmID =1;
			}else{
				if(equipName.toString().equals("ͶӰ��")){
					cgqlmID = 2;
				}else{
					if(equipName.toString().equals("�Ž�")){
						cgqlmID = 6;
					}else{
						if(equipName.toString().equals("ӫ���")){
							cgqlmID = 12;
						}else{
							cgqlmID = 0;
						}
					}
				}
			}
		}
		//����,�������,Ĭ������
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
