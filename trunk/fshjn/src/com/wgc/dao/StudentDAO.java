package com.wgc.dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgc.beans.Student;

public class StudentDAO extends HibernateDaoSupport  {
	   

    
    public void save(Student stu) {

    	this.getHibernateTemplate().save(stu);
    }
    
	public void delete(Student stu) {

        this.getHibernateTemplate().delete(stu);
    }
    
    public Student findById(java.lang.String id) {

            Student stu = (Student)this.getHibernateTemplate().get("com.wgc.beans.Student", id);
            return stu;

    }
    
    
    @SuppressWarnings("unchecked")
	public List findByExample(Student stu) {
    	
            List results = getHibernateTemplate().findByExample(stu);
            return results;
 
    }    
    
    @SuppressWarnings("unchecked")
	public List findByProperty(String propertyName, Object value) {

         String queryString = "from Student as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);

	}

	@SuppressWarnings("unchecked")
	public List findByXm(Object xm
	) {
		return findByProperty("xm", xm
		);
	}
	
	@SuppressWarnings("unchecked")
	public List findByXb(Object xb
	) {
		return findByProperty("xb", xb
		);
	}
	@SuppressWarnings("unchecked")
	public List<Student> findByBjm(Object bjm) {
		return (List<Student>)this.findByProperty("bjm", bjm);
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