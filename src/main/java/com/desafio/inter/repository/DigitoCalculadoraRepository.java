package com.desafio.inter.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import com.desafio.inter.model.DigitoCalculadora;
import com.desafio.inter.model.Usuario;


	@Repository
	public interface DigitoCalculadoraRepository extends JpaRepository<DigitoCalculadora, Integer>, QueryByExampleExecutor<DigitoCalculadora> {
		List<DigitoCalculadora> findByUsuario(Usuario usuario);
		
	}
	