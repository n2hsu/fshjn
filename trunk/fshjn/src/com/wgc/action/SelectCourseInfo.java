package com.wgc.action;

import java.util.Calendar;
import java.util.List;

import com.wgc.beans.CoursesInfo;
import com.wgc.service.CoursesInfoService;

public class SelectCourseInfo {
	
	private CoursesInfoService courInfoService;
	private Short jcS = 0;
	
	public List<CoursesInfo> selectCour(String jsh,String jc ,String xq){
		try{//处理jc为空无法转换
			if(jc==null){
				jcS = 0;
			}else{
				jcS =Short.parseShort(jc);
			}if(jsh.equals("NaN")){//此处为暂时处理，原因有待考虑
				jsh = "";
			}if(xq==null){//此处必须为==，equal报错
				xq="";
			}		
			return this.courInfoService.findByJcJshXq(jsh, jcS, xq.trim());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	
	public List<CoursesInfo> selectClassCour(String jsh){
				
		try{//处理节数为空无法转换
			if(jsh.equals("NaN")){//此处为暂时处理，原因有待考虑
				jsh = "";
			}
			return this.courInfoService.findByJcJshXq(jsh, this.getJch(), this.getXq());	
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	//根据教学楼查询当前时间的课程信息
	public List<CoursesInfo> selectClassRoCourses(String lm){
		return this.courInfoService.findByLmJsZc(this.getLm(lm), this.getJch(), this.getXq()); 
	}
	//封装当前时间转换节次数
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
	//封装星期的转换
	private String getXq(){
		String xqM = null;
		Calendar now = Calendar.getInstance();
		switch(now.get(Calendar.DAY_OF_WEEK)){
			case 2:xqM = "周一";break;
			case 3:xqM = "周二";break;
			case 4:xqM = "周三";break;
			case 5:xqM = "周四";break;
			case 6:xqM = "周五";break;
			case 7:xqM = "周六";break;
			case 1:xqM = "周日";break;			
		}
		return xqM;
	}
	//封装楼名楼号的转换
	private String getLm(String lm){
		if(lm.trim().equals("信息楼")){
			return "1";
		}else{
			if(lm.trim().equals("材料楼")){
				return "2";
			}else{
				if(lm.trim().equals("文学楼")){
					return "3";
				}else{
					if(lm.trim().equals("外语楼")){
						return "4";
					}else{
						if(lm.trim().equals("经济楼")){
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
