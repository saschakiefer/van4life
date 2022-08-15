package de.saschakiefer.van4life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "de.saschakiefer.van4life")
public class Van4LifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Van4LifeApplication.class, args);
	}

}
