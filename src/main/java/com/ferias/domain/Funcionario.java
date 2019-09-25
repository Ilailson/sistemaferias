package com.ferias.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
	private Date admissao = new Date();//gera  a data atual na  visão (inputtx  ou output)

	@ManyToOne//Muitos funcionários pode está dentro de uma equipe
	private Equipe equipe;
	
	@ManyToOne//muitos funcionários podem ter o mesmo perfil
	private PerfilUser perfilUser;

	private String login;

	private String senha;
	
	private Integer matricula;
	
	// ============================GET SET=====================================

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public Date getAdmissao() {
		return admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
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

	public PerfilUser getPerfilUser() {
		return perfilUser;
	}

	public void setPerfilUser(PerfilUser perfilUser) {
		this.perfilUser = perfilUser;
	}
	
	public Integer getMatricula() {
		return matricula;
	}
	
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	
}
