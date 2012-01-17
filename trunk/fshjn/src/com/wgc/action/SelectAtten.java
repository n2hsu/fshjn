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
		 * �鿴���ڼ�¼������δ���ڵ�ѧ������Ϣ,ǰ̨�����ܴκͽڴ��Լ��̹��ţ����ݽڴ��ж��Ͽε�ʱ�䣬
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
				
		//���Ȳ鿴���ʱ���ʦ�Ƿ��п�
		if(this.coursesinfoService.findByJsZcJgh(jgh, jc, zc).isEmpty()){ 
			return null;

		}else{
			course = this.coursesinfoService.findByJsZcJgh(jgh, jc, zc);
		
			for(int m = 0 ;m<course.size() ; m++ ){
				String kch = this.course.get(m).getCourse().getKch();
				attend.addAll(this.attenService.findByKch(kch));
				//�����������в����ϲ�ѯ�������޳�
				for(int i = 0 ; i<attend.size() ; i++ ){
					//ת�����ڡ���
					Timestamp time = attend.get(i).getSkrq();
					SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					
					try {
						Date date = dateF.parse(time.toString());
						cal.setTime(date);
						//���ѯ���ܴν��бȽϣ��޳�
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
							//��ѯ�ڴν��бȽϣ��޳�
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
	
	//��װ���ڵ�ת��
	private String getXq(int i){
		String xqM = null;

		switch(i){
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
