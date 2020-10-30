package com.desafio.inter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.inter.exception.ResourceNotFoundException;
import com.desafio.inter.model.DigitoCalculadora;
import com.desafio.inter.model.Usuario;
import com.desafio.inter.repository.DigitoCalculadoraRepository;
import com.desafio.inter.repository.UsuarioRepository;


@Service
@Transactional
public class DigitoCalculadoraServiceImpl implements DigitoCalculadoraService{

	
	@Autowired
	DigitoCalculadoraService digitoUnico;
	
	@Autowired
	DigitoCalculadoraRepository digitoUnicoRepo;
	
	@Autowired
	DigitoCalculadoraRepository usuarioRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public DigitoCalculadora novoCalculo(DigitoCalculadora digito) {
		return digitoUnicoRepo.save(digito);
	}
	
	@Override
	public Integer calcularDigito(String numero) {

		try {
		Integer resultado = 0;
		if(numero.length() == 1) {
			return Integer.parseInt(numero);
		}
	        for(char num: numero.toCharArray()) {                        
	             resultado += Integer.valueOf(Character.toString(num));
	        }
	        if(resultado.toString().length() > 1)
	             return calcularDigito(resultado.toString());
	        
	        return resultado;
	        
	}catch (NumberFormatException e) {
       throw new NumberFormatException("Número inválido!");
	}
}
	
	@Override
	public Integer calcularSequenciaDigito(String numero, Integer multiplicador) {
		
		Integer resultado = 0;
		String numeroConcac = concatenarNumero(numero, multiplicador);
		
		for(char num: numeroConcac.toCharArray()) {
			resultado += Integer.valueOf(Character.toString(num));
		}
		while(resultado.toString().length() > 1) {
			return calcularDigito(resultado.toString());
		}	
		return resultado;
		
	}
	
	@Override
	public String concatenarNumero(String numero, Integer multiplicador) {

        StringBuilder numeroConcac= new StringBuilder();

        for(int i = 0; i < multiplicador; i++){
        	
            numeroConcac.append(numero);
        }
        
        
        return numeroConcac.toString();
	}

	@Override
	public List<DigitoCalculadora> getOperacaoByUserId(Integer usuarioId) {
		Usuario user = usuarioService.getUsuarioById(usuarioId);
		List<DigitoCalculadora> listDigito = digitoUnicoRepo.findByUsuario(user);
		if(listDigito != null && !listDigito.isEmpty())
			return listDigito;
		else
			throw new ResourceNotFoundException("Usuario com ID: " + user.getId() + " nao possui operacoes realizadas.");
	}
		

	@Override
	public void deleteOperacaoDigito(Integer id) {
		Optional<DigitoCalculadora> digitoDb = this.digitoUnicoRepo.findById(id);
		if(digitoDb.isPresent()) {
			this.digitoUnicoRepo.deleteById(id);
		}else {
			throw new ResourceNotFoundException("Operacao nao encontrada.");
		}
		
	}

	
}
