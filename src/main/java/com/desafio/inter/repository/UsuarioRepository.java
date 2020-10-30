package com.desafio.inter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.inter.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	
}

