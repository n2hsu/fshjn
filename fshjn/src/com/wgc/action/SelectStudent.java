package com.wgc.action;

import java.util.ArrayList;
import java.util.List;

import com.wgc.beans.Student;
import com.wgc.service.StudentService;

public class SelectStudent {
	
	private StudentService stuService;
	private List<Student> stuList = new ArrayList<Student>();
	
	//ǰ̨List�����޷����ݵ���̨
	public List<Student> findByXh(List<Student> xhList){
		//ǰ̨���ݺ���ѧ�ŵ�ѧ�����󣬽��˶������ѧ�Ž��в�ѯ��ȡ����Ӧ����ǰ̨ȡ������İ༶����������
		for(int i = 0 ;i<xhList.size() ; i++){
			String xh = (xhList.get(i)).getXh();
			this.stuService.findByStuID(xh);
			stuList.add(this.stuService.findByStuID(xh));
		}
		return stuList;
	}

	public void setStuService(StudentService stuService) {
		this.stuService = stuService;
	}
	public StudentService getStuService() {
		return stuService;
	}
}
