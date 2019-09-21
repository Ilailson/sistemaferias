package com.ferias.domain;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class PerfilUser extends GenericDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	} 
	
	
}
