package com.demo.SpringSecurity;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// Security Configuration
	// DB Configuration
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfig.class, DBConfiguration.class };
	}

	// Servlet Configuration for IOC
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ServletConfig.class };
	}

	// Servlet Mapping (Welcome - File)
	@Override
	protected String[] getServletMappings() {
		String[] str = new String[] { "/" };
		return str;
	}

}
