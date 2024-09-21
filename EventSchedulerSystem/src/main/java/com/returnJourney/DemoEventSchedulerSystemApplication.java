package com.returnJourney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Generated;

@RestController
@SpringBootApplication
public class DemoEventSchedulerSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoEventSchedulerSystemApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void greet() {
		System.out.println("Project on running mode");
	}

}
