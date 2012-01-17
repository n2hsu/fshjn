package com.wgc.dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgc.beans.Teacher;

public class TeacherDAO extends HibernateDaoSupport  {
	   

    
    public void save(Teacher teach) {

    	this.getHibernateTemplate().save(teach);
    }
    
	public void delete(Teacher teach) {

        this.getHibernateTemplate().delete(teach);
    }
    
    public Teacher findById(java.lang.String id) {

    	Teacher teach = (Teacher)this.getHibernateTemplate().get("com.wgc.beans.Teacher", id);
            return teach;

    }
        
    @SuppressWarnings("unchecked")
	public List findByExample(Teacher teach) {
    	
            List results = getHibernateTemplate().findByExample(teach);
            return results;
     }    
    
    @SuppressWarnings("unchecked")
	public List findByProperty(String propertyName, Object value) {

         String queryString = "from Teacher as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);

	}

	@SuppressWarnings("unchecked")
	public List findByXm(Object xm
	) {
		return this.findByProperty("xm", xm
		);
	}
	
	@SuppressWarnings("unchecked")
	public List findByXb(Object xb
	) {
		return findByProperty("xb", xb
		);
	}
	
	@SuppressWarnings("unchecked")
	public List findByXy(Object xy
	) {
		return findByProperty("xy",xy
		);
	}
	
	@SuppressWarnings("unchecked")
	public List findByCsny(Object csny
	) {
		return findByProperty("csny", csny
		);
	}
	

	@SuppressWarnings("unchecked")
	public List findAll() {

			String queryString = "from BuildingInfo";
		 	return getHibernateTemplate().find(queryString);
	}
	
}