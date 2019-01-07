package com.ciclic.duff.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ciclic.duff"})
public class DuffApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuffApplication.class, args);
	}
}

