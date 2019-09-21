package com.ferias.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Equipe extends GenericDomain{

	private String nome;
	
	@Column(name="quantidade_ferias")
	private Integer quantidade_ferias;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade_ferias() {
		return quantidade_ferias;
	}

	public void setQuantidade_ferias(Integer quantidade_ferias) {
		this.quantidade_ferias = quantidade_ferias;
	}

	
		

}
