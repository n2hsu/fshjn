package com.wgc.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgc.beans.Senser_note;

public class Senser_noteDAO extends HibernateDaoSupport {

	public void save(Senser_note sn){
		
		this.getHibernateTemplate().save(sn);
	}
	public void delete(Senser_note sn){
		
		this.getHibernateTemplate().delete(sn);
	}
	@SuppressWarnings("unchecked")
	public List<Senser_note> findAll(){
	
		return this.getHibernateTemplate().find("from Senser_note");
	}
	
    @SuppressWarnings("unchecked")
	public List findByExample(Senser_note sn) {
    	
    	return this.getHibernateTemplate().findByExample(sn);
     }
	
	@SuppressWarnings("unchecked")
	public List<Senser_note> findByPorperty(String propertyName,Object value){
		
		String sql="from Senser_note as model where model."+propertyName+"= ?";
		return this.getHibernateTemplate().find(sql, value);
	}
	public List<Senser_note> findByCgqID(Object cgqID){
		
		return this.findByPorperty("cgqID", cgqID);
	}
	public List<Senser_note> findByCgqz(Object cgqz){
		
		return this.findByPorperty("cgqz",cgqz );
	}
	public List<Senser_note> findByJlsj(Object jlsj){
		
		return this.findByPorperty("jlsj",jlsj);
	}
}
