package com.desafio.inter.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Entity
public class DigitoCalculadora {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column
	private String numero;
	
	@Column
	private Integer multiplo;
	
	@Column
	private Integer digitoUnico;
	
	@ManyToOne
	private Usuario usuario;
	
	public DigitoCalculadora(String numero, Integer multiplo) {
		super();
		this.numero = numero;
		this.multiplo = multiplo;
	}
	public DigitoCalculadora(Integer id, String numero, Usuario usuario) {
		super();
		this.id = id;
		this.numero = numero;
		this.usuario = usuario;
	}
	
	public DigitoCalculadora() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMultiplo() {
		return multiplo;
	}
	public void setMultiplo(Integer multiplo) {
		this.multiplo = multiplo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Integer getDigitoUnico() {
		return digitoUnico;
	}
	public void setDigitoUnico(Integer digitoUnico) {
		this.digitoUnico = digitoUnico;
	}


}
