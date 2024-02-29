package com.spring.soap.datastore;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableJpaRepositories(basePackages = { "com.spring.soap.repository" } )
@ComponentScan(value = "com.spring.soap.*")
@EntityScan(basePackages = { "com.spring.soap.model" })
public class DataStoreSetup {

	@Value("${spring.datasource.url}")
	String datebaseUrl;
	
	@Value("${spring.datasource.username}")
	String databaseUser;
	
	@Value("${spring.datasource.password}")
	String databasePassword;
	
	@Bean
	DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(datebaseUrl);
		dataSource.setUsername(databaseUser);
		dataSource.setPassword(databasePassword);
		return dataSource;
	}
	
	
	
}
