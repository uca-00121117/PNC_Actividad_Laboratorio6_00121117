package com.uca.capas.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.uca.capas.domain.Estudiante;

public interface EstudianteDAO {
	
	//Interfaz que obtiene datos
	
	public List<Estudiante> findAll() throws DataAccessException;
	
	//Interfaz que inserta datos
	
	public void insert(Estudiante estudiante) throws DataAccessException;

	//Borrar
	
	public void delete(Integer codigo) throws DataAccessException;
}