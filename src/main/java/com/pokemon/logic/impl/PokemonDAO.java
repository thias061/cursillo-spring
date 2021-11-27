package com.pokemon.logic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.pokemon.logic.IPokemonDAO;
import com.pokemon.logic.model.PokemonDBException;
import com.pokemon.logic.model.PokemonDBModel;

public class PokemonDAO implements IPokemonDAO{

	private JdbcTemplate jdbcTemplate;

	public PokemonDAO(DataSource datasource) {

		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public String save(PokemonDBModel pokemon) throws PokemonDBException {
		final String INSERT_SQL = "insert into POKEMON (nombre, tipo) values(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update(
					new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL, new String[] {"id"});
							ps.setString(1, pokemon.getNombre());
							ps.setString(2, pokemon.getTipo());
							return ps;
						}
					},
					keyHolder);
		}
		catch(Exception e) {
			throw new PokemonDBException();
		}
		return keyHolder.getKey().toString();
	}

	@Override
	public void update(PokemonDBModel pokemon)  throws PokemonDBException{
		String sql = "UPDATE POKEMON SET nombre=:nombre, tipo=:tipo WHERE id=:id";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pokemon);

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		try {
			template.update(sql, param);   
		}
		catch(Exception e) {
			throw new PokemonDBException();
		}
	}

	@Override
	public PokemonDBModel get(String id)  throws PokemonDBException{
		String sql = "SELECT * FROM POKEMON WHERE id = ?";
		Object[] args = {id};
		try {
			return jdbcTemplate.queryForObject(sql, args,
					BeanPropertyRowMapper.newInstance(PokemonDBModel.class));
		}
		catch(Exception e) {
			throw new PokemonDBException();
		}
	}

	@Override
	public void destroy(String id)   throws PokemonDBException{
		String sql = "DELETE FROM POKEMON WHERE id = ?";
		try {
			jdbcTemplate.update(sql, id);
		}
		catch(Exception e) {
			throw new PokemonDBException();
		}
	}

	@Override
	public List<PokemonDBModel> getAllPokemon()    throws PokemonDBException{

		String sql = "SELECT * FROM POKEMON";
		try {
			return jdbcTemplate.query(sql,
					BeanPropertyRowMapper.newInstance(PokemonDBModel.class));
		}
		catch(Exception e) {
			throw new PokemonDBException();
		}
	}

}
