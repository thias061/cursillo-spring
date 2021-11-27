package com.soprasteria.cursillo.cursillo_spring.simple;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pokemon.web.controller.PokemonController;

@SpringBootTest
public class PokemonApplicationTest {
	@Autowired
	private PokemonController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
