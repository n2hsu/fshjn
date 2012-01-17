package com.wgc.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wgc.beans.Building;

public class BuildingDAO extends HibernateDaoSupport  {
	  
    public void save(Building transientInstance) {

        try {
            getHibernateTemplate().save(transientInstance);

        } catch (RuntimeException re) {
            throw re;
        }
    }
    
	public void delete(Building persistentInstance) {
        try {
            getHibernateTemplate().delete(persistentInstance);

        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public Building findById(java.lang.Short id) {

        try {
            Building instance = (Building) getHibernateTemplate()
                    .get("com.wgc.beans.Building", id);
            return instance;
        } catch (RuntimeException re) {

            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List findByExample(Building instance) {
    	
        try {
            List results = getHibernateTemplate().findByExample(instance);

            return results;
        } catch (RuntimeException re) {

            throw re;
        }
    }    
    
    @SuppressWarnings("unchecked")
	public List<Building> findByProperty(String propertyName, Object value) {

      try {
         String queryString = "from Building as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {

         throw re;
      }
	}


	public List<Building> findByLm(Object lm) {
		
		return findByProperty("lm", lm);
	}
	
	@SuppressWarnings("unchecked")
	public List findByCwzs(Object cwzs
	) {
		return findByProperty("cwzs", cwzs
		);
	}
	
	@SuppressWarnings("unchecked")
	public List findByYycws(Object yycws
	) {
		return findByProperty("yycws", yycws
		);
	}
	
	@SuppressWarnings("unchecked")
	public List findByJss(Object jss
	) {
		return findByProperty("jss", jss
		);
	}
	

	@SuppressWarnings("unchecked")
	public List findAll() {

		try {
			String queryString = "from Building";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {

			throw re;
		}
	}
	
    public Building merge(Building detachedInstance) {

        try {
            Building result = getHibernateTemplate()
                    .merge(detachedInstance);

            return result;
        } catch (RuntimeException re) {

            throw re;
        }
    }

    public void attachDirty(Building instance) {

        try {
            getHibernateTemplate().saveOrUpdate(instance);

        } catch (RuntimeException re) {

            throw re;
        }
    }
    
    public void attachClean(Building instance) {

        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);

        } catch (RuntimeException re) {

            throw re;
        }
    }

}