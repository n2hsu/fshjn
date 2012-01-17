package com.wgc.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgc.beans.CoursesInfo;

public class CoursesInfoDAO extends HibernateDaoSupport {
	
    public void save(CoursesInfo course) {

    	this.getHibernateTemplate().save(course);
    }
    
	public void delete(CoursesInfo course) {

        this.getHibernateTemplate().delete(course);
    }
    
    public CoursesInfo findById(java.lang.Long id) {

    	return (CoursesInfo)this.getHibernateTemplate().get("com.wgc.beans.CoursesInfo", id);
    }
    //封装无条件的查询
	@SuppressWarnings("unchecked")
	public List<CoursesInfo> findAll(){
		
		String sql = "from CoursesInfo ";
		
		return this.getHibernateTemplate().find(sql);	
	}
	//节次，教室号，周次的查询，封装sql语句查询
	@SuppressWarnings("unchecked")
	public List<CoursesInfo> findByJcJshXq(String sql){
		
		return this.getHibernateTemplate().find(sql);
		
	}
	//节次，周次，教工号的查询，封装sql语句的查询
	@SuppressWarnings("unchecked")
	public List<CoursesInfo> findByJghJsXq(String hql){
		
		return this.getHibernateTemplate().find(hql);
	}
    //节次的查询
	@SuppressWarnings("unchecked")
	public List findByJs(Object js) {
		return this.findByProperty("js", js);
	}
	//星期的查询
	@SuppressWarnings("unchecked")
	public List findByXq(Object xq) {
		return findByProperty("xq", xq);
	}
	//班级名的查询
	@SuppressWarnings("unchecked")
	public List findByBjm(Object bjm) {
		return findByProperty("bjm", bjm);
	}
	//教室号的查询
	@SuppressWarnings("unchecked")
	public List findByJsh(Object jsh) {
		return findByProperty("jsh", jsh);
	}
	@SuppressWarnings("unchecked")
	public List findByExample(CoursesInfo course) {
    	
            List results = getHibernateTemplate().findByExample(course);
            return results;
     }    
    @SuppressWarnings("unchecked")
	public List findByProperty(String propertyName, Object value) {

         String queryString = "from CoursesInfo as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
	}
}
