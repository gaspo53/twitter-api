package com.gaspar.twitter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Besides XML configuration, this feature of Spring 3.1 leaves the possibility of having Environments.

* @author Gaspar Rajoy

*
 */
@Configuration
@PropertySource("classpath:application.properties")
public class TwitterApiConfiguration {

    @Autowired
	private Environment env;
    
    @Bean 
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {  
    	return new PropertySourcesPlaceholderConfigurer();  
    } 
    
	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}
}
