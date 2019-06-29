package com.ferias.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Modelo de férias.
 * 
 * @author Ilailson Rocha
 * @see GenericDomain
 *
 */
@Entity
public class Feria extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@ManyToOne // muitas ferias para um funcionário
	private Funcionario funcionario;

	@Temporal(TemporalType.DATE)
	private Date pInicial = new Date();

	@Temporal(TemporalType.DATE)
	private Date pFinal;

	private String mes;

	private int dias = 20;

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
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(this.getpInicial());// recebendo o dia inicial selecionado pelo usuário.
		calendario.add(Calendar.DAY_OF_MONTH, this.getDias() - 1);// acrescetando quantidade de dias e diminuindo um
																	// pois está acrescentando um dia a mais.

		return calendario.getTime();

	}

	public void setpFinal(Date pFinal) {
		this.pFinal = pFinal;
	}

	public String getMes() {

		Calendar calendario = Calendar.getInstance();
		calendario.setTime(this.getpInicial());
		calendario.add(Calendar.DAY_OF_MONTH, 1);// acrescentando mas um dia, pois quando selecione o dia inicia está
													// com um dia a menos.

		switch (calendario.get(Calendar.MONTH)) {
		case 0:
			mes = "Janeiro";
			break;

		case 1:
			mes = "Fevereiro";
			break;
		case 2:
			mes = "Março";
			break;

		case 3:
			mes = "Abril";
			break;

		case 4:
			mes = "Maio";
			break;

		case 5:
			mes = "Junho";
			break;

		case 6:
			mes = "Julho";
			break;

		case 7:
			mes = "Agosto";
			break;

		case 8:
			mes = "Setembro";
			break;

		case 9:
			mes = "Outubro";
			break;

		case 10:
			mes = "Novembro";
			break;

		case 11:
			mes = "Dezembro";
			break;

		default:
			break;
		}

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
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(this.getpFinal());// pegando o periodo final
		calendario.add(Calendar.DAY_OF_MONTH, 1);// acrestando um dia a mas para o retorno.
		return retorno = calendario.getTime();
	}

	public void setRetorno(Date retorno) {
		this.retorno = retorno;
	}

}
