package com.wgc.action;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.wgc.beans.Attendance;
import com.wgc.beans.CoursesInfo;
import com.wgc.service.AttendanceService;
import com.wgc.service.CoursesInfoService;
import com.wgc.service.StudentService;

		/*
		 * 查看考勤记录，返回未出勤的学生的信息,前台输入周次和节次以及教工号，根据节次判断上课的时间，
		 * 
		 */
public class SelectAtten {
	
	private AttendanceService attenService;
	private CoursesInfoService coursesinfoService;
	private  List<CoursesInfo> course = new ArrayList<CoursesInfo>();
	private StudentService studentService;
	private List<Attendance> attend;

	public List<Attendance> findByQqjl(String zc,String jc,String jgh){
		
		Short jcS = 0;
		int begin = 0;
		int end = 0;
		attend = new ArrayList<Attendance>();
				
		//首先查看这个时间教师是否有课
		if(this.coursesinfoService.findByJsZcJgh(jgh, jc, zc).isEmpty()){ 
			return null;

		}else{
			course = this.coursesinfoService.findByJsZcJgh(jgh, jc, zc);
		
			for(int m = 0 ;m<course.size() ; m++ ){
				String kch = this.course.get(m).getCourse().getKch();
				attend.addAll(this.attenService.findByKch(kch));
				//将所有数据中不符合查询条件的剔除
				for(int i = 0 ; i<attend.size() ; i++ ){
					//转换日期、周
					Timestamp time = attend.get(i).getSkrq();
					SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					
					try {
						Date date = dateF.parse(time.toString());
						cal.setTime(date);
						//与查询的周次进行比较，剔除
						if(this.getXq(cal.get(Calendar.DAY_OF_WEEK)).equals(zc.trim())){
							attend.remove(i);
						}else{
							try{
								jcS = Short.parseShort(jc.trim());
							}catch(NumberFormatException nfe){
								nfe.printStackTrace();
							}
			// 8:00----480;9:35----575;10:15---615;11:50---710; 13:30---810;15:05---905;
			// 15:45---945;17:20---1040;18:00---1080;19:35---1175;	
							//查询节次进行比较，剔除
							switch(jcS){
								case 1:begin = 480;end = 575;break;
								case 2:begin = 615;end = 710;break;
								case 3:begin = 810;end = 905;break;
								case 4:begin = 945;end = 1040;break;
								case 5:begin = 1080;end = 1175;break;
							}
							int minu = cal.get(Calendar.HOUR_OF_DAY)*60+cal.get(Calendar.MINUTE);
							if(!(minu>=begin&&minu<=end)){
								attend.remove(i);
							}
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			
			System.out.println(attend.size());
			
			return attend;
		}
	}
	
	//封装星期的转换
	private String getXq(int i){
		String xqM = null;

		switch(i){
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
	
	public AttendanceService getAttenService() {
		return attenService;
	}
	public void setAttenService(AttendanceService attenService) {
		this.attenService = attenService;
	}
	public CoursesInfoService getCoursesinfoService() {
		return coursesinfoService;
	}
	public void setCoursesinfoService(CoursesInfoService coursesinfoService) {
		this.coursesinfoService = coursesinfoService;
	}
	public StudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
}
