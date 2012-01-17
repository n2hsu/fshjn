package com.wgc.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgc.beans.Course;

public class CourseDAO extends HibernateDaoSupport {
	
    public void save(Course course) {

    	this.getHibernateTemplate().save(course);
    }
    
	public void delete(Course course) {

        this.getHibernateTemplate().delete(course);
    }
    
    public Course findById(java.lang.Integer id) {

    	 return (Course)this.getHibernateTemplate().get("com.wgc.beans.Course", id);
           
    }
    
	@SuppressWarnings("unchecked")
	public List<Course> findAll(){
		
		String sql = "from Course ";
		
		return this.getHibernateTemplate().find(sql);
		
	}
	@SuppressWarnings("unchecked")
	public List findByExample(Course course) {
    	
            List results = getHibernateTemplate().findByExample(course);
            return results;
     }    
    
    @SuppressWarnings("unchecked")
	public List findByProperty(String propertyName, Object value) {

         String queryString = "from Course as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);

	}

	@SuppressWarnings("unchecked")
	public List findByKcm(Object kcm) {
		return findByProperty("kcm", kcm);
	}
}
