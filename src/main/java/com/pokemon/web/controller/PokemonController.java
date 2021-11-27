package com.pokemon.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.api.Pokemon;
import com.pokemon.api.PokemonServiceException;
import com.pokemon.process.IPokemonService;

@RestController
public class PokemonController  {
	private final IPokemonService pokemonService;

	public PokemonController(IPokemonService repository) {
		this.pokemonService = repository;
	}

	// Aggregate root
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pokemons")
	List<Pokemon> all() throws PokemonServiceException{
		return pokemonService.getAllPokemon();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pokemons")
	Pokemon savePokemon(@RequestBody Pokemon pokemon) throws PokemonServiceException{
		String idPokemon = pokemonService.save(pokemon);
		return pokemonService.get(idPokemon);
	}

	// Single item
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pokemons/{id}")
	Pokemon getPokemon(@PathVariable String id) throws PokemonServiceException{

		return pokemonService.get(id);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pokemons/{id}")
	Pokemon updatePokemon(@RequestBody Pokemon pokemon) throws PokemonServiceException{ 

		pokemonService.update(pokemon);
		return pokemonService.get(pokemon.getId());
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("{id}")
	void deleteEmployee(@PathVariable String id) throws PokemonServiceException{
		pokemonService.destroy(id);
	}
}
