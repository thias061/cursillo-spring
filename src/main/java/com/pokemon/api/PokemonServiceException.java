package com.pokemon.api;

import org.springframework.web.bind.annotation.ControllerAdvice;

import com.pokemon.logic.model.PokemonDBException;

public class PokemonServiceException extends Exception{

	public PokemonServiceException(PokemonDBException e) {
		// TODO Auto-generated constructor stub
	}

}
