package com.inmemoryDb.servlet3;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.inmemoryDb.config.SpringRootConfig;
import com.inmemoryDb.config.SpringWebConfig;

public class MyWebInitializer //extends AbstractAnnotationConfigDispatcherServletInitializer
{/*

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;//new Class[] { SpringRootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;//new Class[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.setInitParameter("spring.profiles.active", "hsql");
	}

*/}