package com.example.fabel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FabelApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabelApplication.class, args);
	}

	@GetMapping("/")
	public String rootRouter(){
		return "Alive";
	}
}
