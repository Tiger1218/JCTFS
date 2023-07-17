package com.ddteam.JCTFS;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.ddteam.JCTFS")
// @ConfigurationProperties(prefix="spring.production-datasource")
// @MapperScan("com.ddteam.JCTFS")
// @RestController

public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
