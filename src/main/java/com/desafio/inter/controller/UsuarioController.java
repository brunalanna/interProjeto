package com.desafio.inter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.desafio.inter.model.Usuario;
import com.desafio.inter.service.UsuarioService;


	
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> getAllUsuarios(){
		return ResponseEntity.ok().body(usuarioService.getAllUsuarios());
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id){
		return ResponseEntity.ok().body(usuarioService.getUsuarioById(id));
	}
	
	@PostMapping("/usuario/save")
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
		return ResponseEntity.ok().body(this.usuarioService.salvarUsuario(usuario));
	}
	
	@PutMapping("/usuario/update/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario){
		usuario.setId(id);
		return ResponseEntity.ok().body(this.usuarioService.updateUsuario(usuario));
	}

	@DeleteMapping("/usuario/delete/{id}")
	public HttpStatus deleteUsuario(@PathVariable Integer id)
	{
		this.usuarioService.deleteUsuario(id);
		return HttpStatus.OK;
	}
	

}
