package com.wgc.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import flex.messaging.FactoryInstance;
import flex.messaging.FlexFactory;
import flex.messaging.config.ConfigMap;
import flex.messaging.services.ServiceException;

public class FlexSpringFactory implements FlexFactory {

	
	public FactoryInstance createFactoryInstance(String id, ConfigMap properties) {
		
        SpringFactoryInstance instance = new SpringFactoryInstance(this, id, properties);
        instance.setSource(properties.getPropertyAsString("source", instance.getId()));
        
        return instance;
	}

	public Object lookup(FactoryInstance inst) {
		
        SpringFactoryInstance factoryInstance = (SpringFactoryInstance) inst;
        return factoryInstance.lookup();
	}

	public void initialize(String id, ConfigMap configMap) {}

	
	
	
	static class SpringFactoryInstance extends FactoryInstance{
		
        SpringFactoryInstance(FlexSpringFactory factory, String id, ConfigMap properties){
        super(factory, id, properties);
        }
        
		@Override
		public String toString(){
            return "SpringFactory instance for id=" + getId() + " source=" + getSource() + " scope=" + getScope();
        }
		
		@Override
		public Object lookup() 
        {
        	//È¡µÃbean
            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(flex.messaging.FlexContext.getServletConfig().getServletContext());
            String beanName = getSource();

            try {
                return appContext.getBean(beanName);
            }catch (NoSuchBeanDefinitionException nexc){
                ServiceException e = new ServiceException();
                String msg = "Spring service named '" + beanName + "' does not exist.";
                e.setMessage(msg);
                e.setRootCause(nexc);
                e.setDetails(msg);
                e.setCode("Server.Processing");
                throw e;
            } catch (BeansException bexc) {
                ServiceException e = new ServiceException();
                String msg = "Unable to create Spring service named '" + beanName + "' ";
                e.setMessage(msg);
                e.setRootCause(bexc);
                e.setDetails(msg);
                e.setCode("Server.Processing");
                throw e;
            } 
        }
    } 
}
