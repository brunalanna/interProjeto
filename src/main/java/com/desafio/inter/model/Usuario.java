package com.desafio.inter.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column(length = 2048, nullable = false)
	private String nome;
	
	@Column(length = 2048, nullable = false, unique = true)
	private String email;


	@OneToMany (targetEntity = DigitoCalculadora.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	@JsonIgnore
	private List<DigitoCalculadora> digitoUnico;
	
	public Usuario(Integer id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		
	}
	public Usuario() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonIgnore
	public List<DigitoCalculadora> getDigitoCalculadora() {
		return digitoUnico;
	}
	public void setDigitoCalculadora(List<DigitoCalculadora> digitoUnico) {
		this.digitoUnico = digitoUnico;
	}

}
