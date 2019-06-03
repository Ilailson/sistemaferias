package com.ferias.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Modelo de férias. 
 * @author Ilailson Rocha
 * @see GenericDomain
 *
 */
@Entity
public class Feria extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne //muitas ferias para um funcionário
	private Funcionario funcionario;
	
	@Temporal(TemporalType.DATE)
	private Date pInicial;
	
	@Temporal(TemporalType.DATE)
	private Date pFinal;
	
	private String mes;
	
	private int dias;
	
	@Temporal(TemporalType.DATE)
	private Date retorno;
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getpInicial() {
		return pInicial;
	}

	public void setpInicial(Date pInicial) {
		this.pInicial = pInicial;
	}

	public Date getpFinal() {
		return pFinal;
	}

	public void setpFinal(Date pFinal) {
		this.pFinal = pFinal;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}
	
	public Date getRetorno() {
		return retorno;
	}
	
	public void setRetorno(Date retorno) {
		this.retorno = retorno;
	}
	
}
