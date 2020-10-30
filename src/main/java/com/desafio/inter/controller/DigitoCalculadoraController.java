package com.desafio.inter.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.inter.exception.ResourceNotFoundException;
import com.desafio.inter.model.DigitoCalculadora;
import com.desafio.inter.model.Usuario;
import com.desafio.inter.repository.DigitoCalculadoraRepository;
import com.desafio.inter.repository.UsuarioRepository;
import com.desafio.inter.service.DigitoCalculadoraService;
import com.desafio.inter.service.UsuarioService;

@RestController
public class DigitoCalculadoraController {

	
	@Autowired
	private DigitoCalculadoraService digitoService;
	
	@Autowired
	private UsuarioService usuarioService;
	

	@Autowired
	private DigitoCalculadoraRepository digitoRepository;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/digito/{id}")
	public List<DigitoCalculadora> getOperacaoByUserId(@PathVariable Integer id){
		List<DigitoCalculadora> listaDigitoUnico = digitoService.getOperacaoByUserId(id);		
		return (List<DigitoCalculadora>) listaDigitoUnico;	
	}
	
	@PostMapping("digito/calcular")
	public ResponseEntity<DigitoCalculadora> calcularDigito(@RequestBody DigitoCalculadora digito){	
	
				
		Integer result = digitoService.calcularDigito(digito.getNumero());		
		
		digito.setDigitoUnico(result);

		
		
		Usuario user = new Usuario();
		user = digito.getUsuario();
		

		try{
				usuarioService.getUsuarioById(user.getId());
				
				List<DigitoCalculadora> calculoList = new ArrayList<>();
				calculoList.add(digito);
				user.setDigitoCalculadora(calculoList);
				
		}catch(Exception e) {
			throw new ResourceNotFoundException("Usuario nao cadastrado!");
		} 
		usuarioService.salvarUsuario(user);
		return ResponseEntity.ok().body(digitoRepository.save(digito));
		
	}
	
	@PostMapping("digito/calcular-sequencia")
	public ResponseEntity<DigitoCalculadora> calcularSequenciaDigito(@RequestBody DigitoCalculadora digito){	
	
				
		Integer result = digitoService.calcularSequenciaDigito(digito.getNumero(), digito.getMultiplo());
		
		digito.setDigitoUnico(result);

		
		
		Usuario user = new Usuario();
		user = digito.getUsuario();
		

		try{
				usuarioService.getUsuarioById(user.getId());
				
				List<DigitoCalculadora> calculoList = new ArrayList<>();
				calculoList.add(digito);
				user.setDigitoCalculadora(calculoList);
				
		}catch(Exception e) {
			throw new ResourceNotFoundException("Usuario nao cadastrado!");
		} 
		usuarioService.salvarUsuario(user);
		return ResponseEntity.ok().body(digitoRepository.save(digito));
		
	}
	
}
