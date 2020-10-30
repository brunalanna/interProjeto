package com.desafio.inter.service;

import java.util.List;

import com.desafio.inter.model.DigitoCalculadora;
import com.desafio.inter.model.Usuario;


public interface DigitoCalculadoraService {
	
	public DigitoCalculadora novoCalculo(DigitoCalculadora digito);
	
	Integer calcularDigito(String numero);
	
	Integer calcularSequenciaDigito(String numero, Integer multiplicador);
	
	String concatenarNumero(String numero, Integer multiplicador);

	List<DigitoCalculadora> getOperacaoByUserId(Integer usuarioId);

	void deleteOperacaoDigito(Integer id);

}
