package com.olsa.utility;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext appContext;
	
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		setAppContext(arg0);
	}
	
	private static void setAppContext(ApplicationContext arg0) throws BeansException { 
		appContext=arg0;
	}
	
	public static ApplicationContext getApplicationContext(){
		return appContext;
	}

}
