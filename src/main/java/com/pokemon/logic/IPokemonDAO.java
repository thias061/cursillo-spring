package com.pokemon.logic;

import java.util.List;

import com.pokemon.logic.model.PokemonDBException;
import com.pokemon.logic.model.PokemonDBModel;

public interface IPokemonDAO {

	// CRUD
	
	String save(PokemonDBModel pokemon) throws PokemonDBException;
	
	void update(PokemonDBModel pokemon)throws PokemonDBException;
	
	PokemonDBModel get(String id) throws PokemonDBException;
	
	void destroy(String id) throws PokemonDBException;
	
	List<PokemonDBModel> getAllPokemon() throws PokemonDBException;
	
	
}
