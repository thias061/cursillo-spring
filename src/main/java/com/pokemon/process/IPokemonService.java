package com.pokemon.process;

import java.util.List;

import com.pokemon.api.Pokemon;
import com.pokemon.api.PokemonServiceException;
import com.pokemon.logic.model.PokemonDBModel;

public interface IPokemonService {

	String save(Pokemon pokemon) throws PokemonServiceException;
	
	void update(Pokemon pokemon) throws PokemonServiceException;
	
	Pokemon get(String id) throws PokemonServiceException;
	
	void destroy(String id) throws PokemonServiceException;
	
	List<Pokemon> getAllPokemon() throws PokemonServiceException;
	
}
