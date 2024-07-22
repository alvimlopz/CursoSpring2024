package com.example.demo.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.services.DBservice;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBservice bdBservice;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	public boolean instantialDatabase() throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false;
		}
		
		bdBservice.instantiateTestDatabase();
		
		return true;
	}

}
