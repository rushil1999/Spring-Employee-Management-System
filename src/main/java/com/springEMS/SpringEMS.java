package com.springEMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//Sets up default configuration, starts Tomcat server, does a class path scan, starts spring application context
@SpringBootApplication(scanBasePackages = {"com.springEMS.team", "com.springEMS.employee"})
public class SpringEMS {

	public static void main(String[] args) {
		SpringApplication.run(SpringEMS.class, args);
	}

}
//@ComponentScan(basePackages = {"com.springEMS.employee", "com.springEMS.team"})