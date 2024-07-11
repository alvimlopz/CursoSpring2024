package com.example.demo.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.services.DBservice;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBservice bdBservice;
	
	public boolean instantialDatabase() throws ParseException {
		
		bdBservice.instantiateTestDatabase();
		
		return true;
	}

}
