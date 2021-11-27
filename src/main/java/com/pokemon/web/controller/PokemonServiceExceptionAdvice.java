package com.pokemon.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pokemon.api.PokemonServiceException;

@ControllerAdvice
public class PokemonServiceExceptionAdvice {
	@ResponseBody
	@ExceptionHandler(PokemonServiceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String pokemonNotFoundHandler(PokemonServiceException ex) {
		return ex.getMessage();
	}
}
