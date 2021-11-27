package com.pokemon.web.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.pokemon.logic.IPokemonDAO;
import com.pokemon.logic.impl.PokemonDAO;
import com.pokemon.process.IPokemonService;
import com.pokemon.process.impl.PokemonService;
import com.pokemon.web.controller.PokemonController;

@Configuration
public class PokemonServerConfiguration {

	@Bean
	public DataSource h2DataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("createPokemonTable.sql")
				.build();
	}
	
	@Bean
	public IPokemonDAO pokemonDAO(DataSource ds) {
		return new PokemonDAO(ds);
	}
	
	@Bean
	public IPokemonService pokemonService(IPokemonDAO dao) {
		return new PokemonService(dao);
	}
	
}
