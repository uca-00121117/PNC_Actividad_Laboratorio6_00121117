package com.uca.capas.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uca.capas.domain.Estudiante;

@Repository
public class EstudianteDAOImpl implements EstudianteDAO {
	
	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;
	
	//	Metodo Obtener datos
	@Override
	public List<Estudiante> findAll() throws DataAccessException {
		
		StringBuffer sb = new StringBuffer();
		// Aqui traemos todos los datos 
		sb.append("SELECT * FROM public.estudiante");
		Query query = entityManager.createNativeQuery(sb.toString(), Estudiante.class);
		List<Estudiante> resultset = query.getResultList();
		
		return resultset;
		
	}

	//	Metodo para insertar Datos ;
	//Transactional garantiza a atomicidad de la consulta
	@Override
	@Transactional
	public void insert(Estudiante estudiante) throws DataAccessException {
		// objeto persiste en la base
		entityManager.persist(estudiante);		
		
	}
	@Override
	@Transactional
	public void delete(Integer codigo) throws DataAccessException {
		
		Estudiante estudiante = entityManager.find(Estudiante.class, codigo);
		entityManager.remove(estudiante);

	}


}