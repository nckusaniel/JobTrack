package com.jobtrack;

import com.jobtrack.user.User;
import com.jobtrack.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobtrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobtrackApplication.class, args);
	}


}
