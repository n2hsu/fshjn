package com.wgc.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgc.beans.Attendance;

public class AttendanceDAO extends HibernateDaoSupport {

	public void save(Attendance atte){
		
		 this.getHibernateTemplate().save(atte);
	}
	public void delete(Attendance atte){
		
		this.getHibernateTemplate().delete(atte);
	}
	@SuppressWarnings("unchecked")
	public List<Attendance> findAll(){
	
		String sql = "from Attendance";
		return this.getHibernateTemplate().find(sql);

	}
	
    @SuppressWarnings("unchecked")
	public List findByExample(Attendance atte) {
    	
    	return this.getHibernateTemplate().findByExample(atte);
     }
	
	@SuppressWarnings("unchecked")
	public List<Attendance> findByProperty(String propertyName,Object value){
		
		String sql = "from Attendance as model where model."+propertyName+"= ?";
		return this.getHibernateTemplate().find(sql, value);		
	}
	public List<Attendance> findByXjh(Object xjh){
		
		return this.findByProperty("xjh", xjh);
	}
	public List<Attendance> findByKch(Object kch){
		try{
			return this.findByProperty("course.kch", kch);
		}catch(RuntimeException e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Attendance> findBySkrq(Object skrq){
		
		return this.findByProperty("skrq",skrq);
	}
	public List<Attendance> findByJlsj(Object jlsj){
		
		return this.findByProperty("jlsj", jlsj);
	}
}
