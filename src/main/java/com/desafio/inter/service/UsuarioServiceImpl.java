package com.desafio.inter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.inter.exception.ResourceNotFoundException;
import com.desafio.inter.model.Usuario;
import com.desafio.inter.repository.DigitoCalculadoraRepository;
import com.desafio.inter.repository.UsuarioRepository;


@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	DigitoCalculadoraService digitoUnico;
	
	@Autowired
	DigitoCalculadoraRepository digitoUnicoRepo;

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario updateUsuario(Usuario usuario) {
		Optional<Usuario> usuarioDb = this.usuarioRepository.findById(usuario.getId());
		
		if(usuarioDb.isPresent()) {
			Usuario usuarioUpdate = usuarioDb.get();
			
			usuarioUpdate.setId(usuario.getId());
			usuarioUpdate.setNome(usuario.getNome());
			usuarioUpdate.setEmail(usuario.getEmail());
			
			usuarioRepository.save(usuarioUpdate);
			
			return usuarioUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + usuario.getId());
		}		
	}

	@Override
	public List<Usuario> getAllUsuarios() {
		return this.usuarioRepository.findAll();
	}

	@Override
	public Usuario getUsuarioById(Integer usuarioId) {
		Optional<Usuario> usuarioDb = this.usuarioRepository.findById(usuarioId);
		
		if(usuarioDb.isPresent()) {
			return usuarioDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + usuarioId);
		}	
	}

	@Override
	public void deleteUsuario(Integer id) {
		Optional<Usuario> usuarioDb = this.usuarioRepository.findById(id);
		
		if(usuarioDb.isPresent()) {
			usuarioRepository.delete(usuarioDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found : " + usuarioDb.get());
		}
		
	}

}
