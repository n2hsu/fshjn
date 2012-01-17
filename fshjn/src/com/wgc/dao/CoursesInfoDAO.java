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
    //��װ�������Ĳ�ѯ
	@SuppressWarnings("unchecked")
	public List<CoursesInfo> findAll(){
		
		String sql = "from CoursesInfo ";
		
		return this.getHibernateTemplate().find(sql);	
	}
	//�ڴΣ����Һţ��ܴεĲ�ѯ����װsql����ѯ
	@SuppressWarnings("unchecked")
	public List<CoursesInfo> findByJcJshXq(String sql){
		
		return this.getHibernateTemplate().find(sql);
		
	}
	//�ڴΣ��ܴΣ��̹��ŵĲ�ѯ����װsql���Ĳ�ѯ
	@SuppressWarnings("unchecked")
	public List<CoursesInfo> findByJghJsXq(String hql){
		
		return this.getHibernateTemplate().find(hql);
	}
    //�ڴεĲ�ѯ
	@SuppressWarnings("unchecked")
	public List findByJs(Object js) {
		return this.findByProperty("js", js);
	}
	//���ڵĲ�ѯ
	@SuppressWarnings("unchecked")
	public List findByXq(Object xq) {
		return findByProperty("xq", xq);
	}
	//�༶���Ĳ�ѯ
	@SuppressWarnings("unchecked")
	public List findByBjm(Object bjm) {
		return findByProperty("bjm", bjm);
	}
	//���ҺŵĲ�ѯ
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
