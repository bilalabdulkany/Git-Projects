package com.ef;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@Configuration
@ComponentScan("com.ef.parser")
public class Parser {

	
	@Bean
	public ParserConfig getParser() {
		return new ParserConfig();
	}
	public static void main(String[] args) {
		PropertySource ps = new SimpleCommandLinePropertySource(args);
	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Parser.class);		
		//SpringApplication context =new SpringApplication(Parser.class)
		
		context.getEnvironment().getPropertySources().addFirst(ps);
		//context.getEnvironment().getPropertySources().forEach(System.out::println);
		ParserConfig parser= context.getBean(ParserConfig.class);
		parser.configureParameters();

	}
}
