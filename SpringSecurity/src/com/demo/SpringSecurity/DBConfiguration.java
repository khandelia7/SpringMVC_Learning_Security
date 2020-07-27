package com.demo.SpringSecurity;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"com.demo.repository"})
@ComponentScan(basePackages={"com.demo.SpringSecurity"})
public class DBConfiguration {

	public DataSource setDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/SecDB");
		ds.setUsername("root");
		ds.setPassword("");
		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entity = new LocalContainerEntityManagerFactoryBean();

		// step 1 give datasource to entity
		entity.setDataSource(setDataSource());

		// step 2: give JpaVendorAdapter
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		entity.setJpaVendorAdapter(adapter);
		entity.setPackagesToScan("com.demo.Controller","com.demo.SpringSecurity","com.demo.repository","com.demo.Model"); //IMPORTANT

		// step 3: Give properties to entity
		Properties prop = new Properties();
		prop.setProperty("hibernate.hbm2ddl.auto", "update"); // none if dataBase is there //create?
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"); // Dialect
		prop.setProperty("hibernate.show_sql", "true"); //It show SQL QUERY in the console
		entity.setJpaProperties(prop); 
		
		return entity;
	}
	
	@Bean
	//You can write JpaTransactionManager instead of PlatformTransactionManager
	public PlatformTransactionManager transactionManager(EntityManagerFactory entity){
		JpaTransactionManager manager = new JpaTransactionManager();
		//provide entity to manager
		manager.setEntityManagerFactory(entity);
		return manager;
	}
}
