package com.jobtrack;

import com.jobtrack.model.User;
import com.jobtrack.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobtrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobtrackApplication.class, args);
	}

	// 加入啟動後自動建立假資料的程式
	@Bean
	public CommandLineRunner demoData(UserRepository userRepository) {
		return args -> {
			if (userRepository.count() == 0) {
				userRepository.save(new User("john", "john@example.com"));
				userRepository.save(new User("alice", "alice@example.com"));
			}
		};
	}
}
