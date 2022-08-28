package de.saschakiefer.van4life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@SpringBootApplication(scanBasePackages = "de.saschakiefer.van4life")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@Theme(themeClass = Lumo.class, variant = Lumo.DARK)
public class Van4LifeApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(Van4LifeApplication.class, args);
	}

}
