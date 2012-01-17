package com.wgc.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgc.beans.Car_style;

public class Car_styleDAO extends HibernateDaoSupport {

	public void save(Car_style cs){
		
		this.getHibernateTemplate().save(cs);
	}
	public void delete(Car_style cs){
		
		this.getHibernateTemplate().delete(cs);
	}
	@SuppressWarnings("unchecked")
	public List<Car_style> findAll(){
		
		return this.getHibernateTemplate().find("from Car_style");
	}
	public Car_style findById(java.lang.String id){
		
		return (Car_style)this.getHibernateTemplate().get("com.wgc.beans.Car_style", id);
	}
	
    @SuppressWarnings("unchecked")
	public List findByExample(Car_style cs) {

    	return this.getHibernateTemplate().findByExample(cs);
    }
	
	@SuppressWarnings("unchecked")
	public List<Car_style> findByPorperty(String propertyName,Object value){
		
		String sql = "from Car_style as model where model."+propertyName+"= ?";
		return this.getHibernateTemplate().find(sql, value);
	}
	public List<Car_style> findByJgh(Object jgh){
		
		return this.findByPorperty("jgh", jgh);
	}
	public List<Car_style> findByClpp(Object clpp){
		
		return this.findByPorperty("clpp", clpp);
	}
	public List<Car_style> findByCllx(Object cllx){
		
		return this.findByPorperty("cllx", cllx);
	}
}
