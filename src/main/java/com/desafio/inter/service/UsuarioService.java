package com.desafio.inter.service;

import java.util.List;

import com.desafio.inter.model.Usuario;

public interface UsuarioService {
	


	Usuario salvarUsuario(Usuario usuario);

	Usuario updateUsuario(Usuario usuario);

	List<Usuario> getAllUsuarios();

	Usuario getUsuarioById(Integer usuarioId);

	void deleteUsuario(Integer id);
	
}
