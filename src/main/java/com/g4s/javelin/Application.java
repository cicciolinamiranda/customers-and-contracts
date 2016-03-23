package com.g4s.javelin;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Application {

	/*
	 * (non-Javadoc) Application configuration "entry-point" is patterned after
	 * Spring-boot.
	 */
}
