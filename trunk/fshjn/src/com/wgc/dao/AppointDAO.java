package com.wgc.dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.wgc.beans.Appoint;

public class AppointDAO extends HibernateDaoSupport {

	public void save(Appoint appo){
		
		 this.getHibernateTemplate().save(appo);
	}
	public void delete(Appoint appo){
		
		this.getHibernateTemplate().delete(appo);
	}
    public Appoint findById(int id) {

    	Appoint appo = (Appoint)this.getHibernateTemplate().get("com.wgc.beans.Appoint", id);
            return appo;

    }
	@SuppressWarnings("unchecked")
	public List<Appoint> findAll(){
	
		String sql = "from Appoint";
		return this.getHibernateTemplate().find(sql);

	}
	@SuppressWarnings("unchecked")
	public List<Appoint> find(String hql){

		return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<Appoint> findByProperty(String propertyName,Object value){
		
		String sql = "from Appoint as model where model."+propertyName+"= ?";
		return this.getHibernateTemplate().find(sql, value);		
	}
	public List<Appoint> findByShzt(Boolean shzt){
		
		return this.findByProperty("shzt", shzt);
	}
	public List<Appoint> findByYyjs(Object yyjs){
		
		return this.findByProperty("yyjs",yyjs);
	}
	public List<Appoint> findByYysj(Object yysj){
		
		return this.findByProperty("yysj", yysj);
	}
	public List<Appoint> findByJs(Object js){
		
		return this.findByProperty("js", js);
	}
}
