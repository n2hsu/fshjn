package com.wgc.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgc.beans.Senser_style;

public class Senser_styleDAO extends HibernateDaoSupport {

	public void save(Senser_style ss){
		
		this.getHibernateTemplate().save(ss);
	}
	public void delete(Senser_style ss){
		
		this.getHibernateTemplate().delete(ss);
	}
	@SuppressWarnings("unchecked")
	public List<Senser_style> findAll(){
	
		String sql = "from Senser_style";
		return this.getHibernateTemplate().find(sql);		
	}
	public Senser_style findById(java.lang.Integer id){
		
		return (Senser_style)this.getHibernateTemplate().get("com.wgc.beans.Senser_style", id);
	}
	
    @SuppressWarnings("unchecked")
	public List findByExample(Senser_style ss) {
    	
            List results = getHibernateTemplate().findByExample(ss);
            return results;
     }
	
	@SuppressWarnings("unchecked")
	public List<Senser_style> findByPorperty(String propertyName,Object value){
		
		String sql = "from Senser_style as model where model."+propertyName+"= ?";
		
		return this.getHibernateTemplate().find(sql, value);
	}
	public List<Senser_style> findByCgqlxm(Object cgqlxm){
		
		return this.findByPorperty("cgqlxm", cgqlxm);
	}
	public List<Senser_style> findByMnl(Object mnl){
		
		return this.findByPorperty("mnl", mnl);		
	}
}
