package de.saschakiefer.van4life.persistence.liquibase;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class LiquibaseSchemaCreator {
	private static final String CREATE_SCHEMA_STATEMENT = "CREATE SCHEMA IF NOT EXISTS %s";

	private final SpringLiquibase springLiquibase;

	public void createSchema() {
		final String liquibaseSchemaName = springLiquibase.getDefaultSchema();
		if (liquibaseSchemaName != null) {
			try {
				final JdbcTemplate jdbcTemplate = new JdbcTemplate(springLiquibase.getDataSource());
				log.info("Creating DB schema {} if not existing", liquibaseSchemaName);
				jdbcTemplate.execute(String.format(CREATE_SCHEMA_STATEMENT, liquibaseSchemaName));
				log.info("DB schema created successfully");
			} catch (DataAccessException e) {
				log.error("Failed to create DB schema", e);
				throw e;
			}
		} else {
			log.info("Skipping DB schema creation because no default schema is configured");
		}
	}
}
