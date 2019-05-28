package com.ferias.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Modelo funcionario.
 * 
 * @author Ilailson Rocha
 *
 * @see GenericDomain
 */
@Entity
public class Funcionario extends GenericDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;

	@Temporal(TemporalType.DATE)
	private Date admissao;

	private String equipe;

	private String login;

	private String senha;

	// ============================GET SET=====================================

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getAdmissao() {
		return admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
