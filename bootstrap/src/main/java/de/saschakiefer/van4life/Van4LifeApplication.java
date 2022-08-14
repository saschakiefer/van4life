package de.saschakiefer.van4life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = "de.saschakiefer.van4life")
@ServletComponentScan
public class Van4LifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Van4LifeApplication.class, args);
	}

}
