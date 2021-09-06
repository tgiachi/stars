package com.github.tgiachi.stars.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.github.tgiachi.stars")
public class StarsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarsServerApplication.class, args);
	}

}
