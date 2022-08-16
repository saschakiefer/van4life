package de.saschakiefer.van4life.persistence.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.saschakiefer.van4life.persistence.liquibase.LiquibaseBeanPostProcessor;
import de.saschakiefer.van4life.persistence.liquibase.LiquibaseSchemaCreator;

@Configuration
public class SpringLiquibaseSchemaConfig {

	@Bean
	public LiquibaseBeanPostProcessor liquibaseSchemaInitPostProcessor() {
		return new LiquibaseBeanPostProcessor((springLiquibase -> {
			new LiquibaseSchemaCreator(springLiquibase).createSchema();
		}));
	}
}
