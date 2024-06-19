package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.rest.user.UserDto;
import com.example.demo.rest.user.UserService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner commandLineRunner(UserService userService) {
	// 	return args -> {
	// 		userService.addUser(new UserDto("John", "john@email.com", "123"));
	// 		userService.addUser(new UserDto("Jane", "jane@email.com", "1234"));
	// 	};
	// }

}
