package com.wgc.action;

import java.util.Calendar;
import java.util.List;

import com.wgc.beans.CoursesInfo;
import com.wgc.service.CoursesInfoService;

public class SelectCourseInfo {
	
	private CoursesInfoService courInfoService;
	private Short jcS = 0;
	
	public List<CoursesInfo> selectCour(String jsh,String jc ,String xq){
		try{//����jcΪ���޷�ת��
			if(jc==null){
				jcS = 0;
			}else{
				jcS =Short.parseShort(jc);
			}if(jsh.equals("NaN")){//�˴�Ϊ��ʱ����ԭ���д�����
				jsh = "";
			}if(xq==null){//�˴�����Ϊ==��equal����
				xq="";
			}		
			return this.courInfoService.findByJcJshXq(jsh, jcS, xq.trim());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	
	public List<CoursesInfo> selectClassCour(String jsh){
				
		try{//�������Ϊ���޷�ת��
			if(jsh.equals("NaN")){//�˴�Ϊ��ʱ����ԭ���д�����
				jsh = "";
			}
			return this.courInfoService.findByJcJshXq(jsh, this.getJch(), this.getXq());	
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	//���ݽ�ѧ¥��ѯ��ǰʱ��Ŀγ���Ϣ
	public List<CoursesInfo> selectClassRoCourses(String lm){
		return this.courInfoService.findByLmJsZc(this.getLm(lm), this.getJch(), this.getXq()); 
	}
	//��װ��ǰʱ��ת���ڴ���
	private Short getJch(){
		
		int nowMinu = 0;
		Calendar now = Calendar.getInstance();
			// 8:00----480;9:35----575;10:15---615;11:50---710; 13:30---810;15:05---905;
			// 15:45---945;17:20---1040;18:00---1080;19:35---1175;	
		nowMinu = now.get(Calendar.HOUR_OF_DAY)*60+now.get(Calendar.MINUTE);

		if(nowMinu>=480&&nowMinu<=575){
			return 1;
		}else{
			if(nowMinu>=615&&nowMinu<=710){
				return 2;
			}else{
				if(nowMinu>=810&&nowMinu<=905){
					return 3;
				}else{
					if(nowMinu>=945&&nowMinu<=1040){
						return 4;
					}else{
						if(nowMinu>=1080&&nowMinu<=1175){
							return 5;
						}else{
							return 10;
						}
					}
				}
			}				
		}
	}
	//��װ���ڵ�ת��
	private String getXq(){
		String xqM = null;
		Calendar now = Calendar.getInstance();
		switch(now.get(Calendar.DAY_OF_WEEK)){
			case 2:xqM = "��һ";break;
			case 3:xqM = "�ܶ�";break;
			case 4:xqM = "����";break;
			case 5:xqM = "����";break;
			case 6:xqM = "����";break;
			case 7:xqM = "����";break;
			case 1:xqM = "����";break;			
		}
		return xqM;
	}
	//��װ¥��¥�ŵ�ת��
	private String getLm(String lm){
		if(lm.trim().equals("��Ϣ¥")){
			return "1";
		}else{
			if(lm.trim().equals("����¥")){
				return "2";
			}else{
				if(lm.trim().equals("��ѧ¥")){
					return "3";
				}else{
					if(lm.trim().equals("����¥")){
						return "4";
					}else{
						if(lm.trim().equals("����¥")){
							return "5";
						}else{
							return null;
						}
					}
				}
			}
		}
	}
	public CoursesInfoService getCourInfoService() {
		return courInfoService;
	}

	public void setCourInfoService(CoursesInfoService courInfoService) {
		this.courInfoService = courInfoService;
	}
}
