package com.wgc.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgc.beans.Senser;

public class SenserDAO extends HibernateDaoSupport {

	public void save(Senser senser){
		
		this.getHibernateTemplate().save(senser);
	}
	public void delete(Senser senser){
		
		this.getHibernateTemplate().delete(senser);
	}
	@SuppressWarnings("unchecked")
	public List<Senser> find(String hql){
		
		return this.getHibernateTemplate().find(hql);
		
	}
	@SuppressWarnings("unchecked")
	public List<Senser> findByLm(String lh){
		
		return this.getHibernateTemplate().find("from Senser where cgqmc like '"+lh+"%'");
	}
	@SuppressWarnings("unchecked")
	public List<Senser> findAll(){
	
		String sql = "from Senser";
		return this.getHibernateTemplate().find(sql);		
	}
	public Senser findById(java.lang.String id){
		
		return (Senser)this.getHibernateTemplate().get("com.wgc.beans.Senser", id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Senser> findByLxBh(String mc,int bh){
		
		String hql = "from Senser senser where senser.cgqmc='"+mc+"' and senser.ss.cgqlxID="+bh;
		return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<Senser> findByLxBh1(String mc,int bh){
		
		String hql1 = "from Senser senser where senser.cgqmc='"+mc+"' and senser.ss.cgqlxID="+bh;
		return this.getHibernateTemplate().find(hql1);
	}
	
	@SuppressWarnings("unchecked")
	public List<Senser> findByProperty(String propertyName,Object value){
		
		String sql = "from Senser as model where model."+propertyName+"= ?";
		
		return this.getHibernateTemplate().find(sql, value);
	}
	public List<Senser> findByCgqmc(Object cgqmc){
		
		return this.findByProperty("cgqmc", cgqmc);
	}
}
