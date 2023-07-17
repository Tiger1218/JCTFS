package com.ddteam.JCTFS;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ddteam.JCTFS")

public class JCTFS {

    /**
     * Main class for the whole application.
     * @param args input from CLI */
    public static void main(final String[] args) {
        SpringApplication.run(JCTFS.class, args);
    }

}
