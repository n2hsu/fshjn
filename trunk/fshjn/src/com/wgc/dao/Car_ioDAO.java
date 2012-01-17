package com.wgc.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgc.beans.Car_io;

public class Car_ioDAO extends HibernateDaoSupport {

	public void save(Car_io cario){
		
		this.getHibernateTemplate().save(cario);
	}
	public void delete(Car_io cario){
		
		this.getHibernateTemplate().delete(cario);
	}
	@SuppressWarnings("unchecked")
	public List<Car_io> findAll(){
		
		return this.getHibernateTemplate().find("from Car_io");
	}

	@SuppressWarnings("unchecked")
	public List<Car_io> findByExample(Car_io cario) {
    	
    		return this.getHibernateTemplate().findByExample(cario);
     }
	
	@SuppressWarnings("unchecked")
	public List<Car_io> findByPorperty(String propertyName,Object value){
		
		String sql = "from Car_io as model where model."+propertyName+"= ?";
		return this.getHibernateTemplate().find(sql, value);
	}
	public List<Car_io> findByCqh(Object cph){
		
		return this.findByPorperty("cph", cph);
	}
	public List<Car_io> findByJczt(Object jczt){
		
		return this.findByPorperty("jczt", jczt);
	}
	public List<Car_io> findByCxm(Object cxm){
		
		return this.findByPorperty("cxm", cxm);
	}
	public List<Car_io> findByJlsj(Object jlsj){
		
		return this.findByPorperty("jlsj", jlsj);
	}

}
