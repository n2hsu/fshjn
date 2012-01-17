package com.wgc.service;

import java.util.List;

import com.wgc.beans.CoursesInfo;
import com.wgc.dao.CoursesInfoDAO;

public class CoursesInfoService {

	private CoursesInfoDAO courseInfo;
	
	public List<CoursesInfo> findAllCourse(){
		return this.courseInfo.findAll();
	}
	public List<CoursesInfo> findByJcJshXq(String jsh,Short jc,String xq){
		String sql = "from CoursesInfo where jsh='"+jsh.trim()+"'";
		
			if(jc!=0){
				sql = sql+" and js="+jc;
				if(!xq.equalsIgnoreCase("")){
					sql = sql+" and xq='"+xq.trim()+"'";
				}
			}else{//������jcΪ����Ȼ��Ҫ�жϺ����Ϊ�����
				if(!xq.equalsIgnoreCase("")){
					sql = sql+" and xq='"+xq.trim()+"'";
				}
			}
		return this.courseInfo.findByJcJshXq(sql);
	}
	//���ݽڴ��ܴν̹��Ų�ѯ
	public List<CoursesInfo> findByJsZcJgh(String jgh,String jc,String zc){
		return this.courseInfo.findByJghJsXq("from CoursesInfo where jgh='"+jgh+"' and xq='"+zc+"' and js="+jc);
	}
	//���ݽ�ѧ¥�Žڴ��ܴβ�ѯ
	public List<CoursesInfo> findByLmJsZc(String lh,Short jc,String xq){	
		return this.courseInfo.findByJcJshXq("from CoursesInfo where jsh like '"+lh+"%' and xq='"+xq+"' and js="+jc);			
	}
	//���ݽڴβ���
	@SuppressWarnings("unchecked")
	public List<CoursesInfo> findByJc(Short jc){
		return this.courseInfo.findByJs(jc);
	}
	//���ݽ��Һ�
	@SuppressWarnings("unchecked")
	public List<CoursesInfo> findByJsh(String jsh){
		return this.courseInfo.findByJsh(jsh);
	}
	//�������ڲ�ѯ
	@SuppressWarnings("unchecked")
	public List<CoursesInfo> findByXq(String xq){
		return this.courseInfo.findByXq(xq);
	}
	public CoursesInfoDAO getCourseInfo() {
		return courseInfo;
	}
	public void setCourseInfo(CoursesInfoDAO courseInfo) {
		this.courseInfo = courseInfo;
	}
	
}
