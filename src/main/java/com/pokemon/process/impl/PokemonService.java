package com.pokemon.process.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pokemon.api.Pokemon;
import com.pokemon.api.PokemonServiceException;
import com.pokemon.logic.IPokemonDAO;
import com.pokemon.logic.model.PokemonDBException;
import com.pokemon.logic.model.PokemonDBModel;
import com.pokemon.process.IPokemonService;


public class PokemonService implements IPokemonService{

	@Autowired
	private IPokemonDAO pokemonDao;
	
	private ModelMapper modelMapper = new ModelMapper();

	public PokemonService(IPokemonDAO pokemonDAO) {
		this.pokemonDao = pokemonDAO;
	}
	
	@Override
	public String save(Pokemon pokemon) throws PokemonServiceException{

		ModelMapper modelMapper = new ModelMapper();
		PokemonDBModel pokemonDB = modelMapper.map(pokemon, PokemonDBModel.class);
		try {
			return pokemonDao.save(pokemonDB);
		} catch (PokemonDBException e) {
			throw new PokemonServiceException(e);
		}
	}

	@Override
	public void update(Pokemon pokemon) throws PokemonServiceException{
		
		PokemonDBModel pokemonDB = modelMapper.map(pokemon, PokemonDBModel.class);
		try {
			pokemonDao.update(pokemonDB);
		} catch (PokemonDBException e) {
			throw new PokemonServiceException(e);
		}
	}

	@Override
	public Pokemon get(String id) throws PokemonServiceException{
		PokemonDBModel pokemonDB;
		try {
			pokemonDB = pokemonDao.get(id);
			return modelMapper.map(pokemonDB, Pokemon.class);
		} catch (PokemonDBException e) {
			throw new PokemonServiceException(e);
		}
		
	}

	@Override
	public void destroy(String id) throws PokemonServiceException {
		try {
			pokemonDao.destroy(id);
		} catch (PokemonDBException e) {
			throw new PokemonServiceException(e);
		}
	}

	@Override
	public List<Pokemon> getAllPokemon() throws PokemonServiceException {
		
		try {
			return pokemonDao.getAllPokemon().stream()
			        .map(entity -> modelMapper.map(entity, Pokemon.class))
			        .collect(Collectors.toList());
		} catch (PokemonDBException e) {
			throw new PokemonServiceException(e);
		}
	}

}
