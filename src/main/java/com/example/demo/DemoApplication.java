package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import com.example.demo.rest.beers.beers.BeerDto;
import com.example.demo.rest.beers.beers.BeersService;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Get beers from API and save them to database
	@Bean
	public CommandLineRunner addBeers(BeersService beersService) {
		return (args) -> {
			BeerDto[] beers = beersService.getBeersFromScratch();
			
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
