package com.jdn.varieties.inventory_control.config.infraestructure;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Bean
	public Flyway flyway(DataSource dataSource) {
		Flyway flyway = Flyway.configure().dataSource(dataSource).locations("classpath:db/migrations")
				.baselineOnMigrate(true).validateMigrationNaming(true).validateOnMigrate(true).cleanDisabled(true)
				.load();
		flyway.migrate();
		return flyway;
	}
}