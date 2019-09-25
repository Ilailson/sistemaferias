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
	
	private int ano;

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

		return pFinal = calendario.getTime(); //atribuindo o valor digitado automaticamente no final das férias para a variavel pfiinal

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
			mes = "JANEIRO";
			break;

		case 1:
			mes = "FEVEREIRO";
			break;
		case 2:
			mes = "MARÇO";
			break;

		case 3:
			mes = "ABRIL";
			break;

		case 4:
			mes = "MAIO";
			break;

		case 5:
			mes = "JUNHO";
			break;

		case 6:
			mes = "JULHO";
			break;

		case 7:
			mes = "AGOSTO";
			break;

		case 8:
			mes = "SETEMBRO";
			break;

		case 9:
			mes = "OUTUBRO";
			break;

		case 10:
			mes = "NOVEMBRO";
			break;

		case 11:
			mes = "DEZEMBRO";
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
		int dia;
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(this.getpFinal());//pegando o periodo final
		dia = calendario.get(Calendar.DAY_OF_WEEK);
		System.out.println("Dia deo retorno "+dia);
		
		int a = calendario.get(Calendar.YEAR);//pegando o ano
		System.out.println("Ano: "+a);
		
		this.ano = a;
		
		if(dia==6) {//se for domingo acrescenta dois dias
			
			calendario.add(Calendar.DAY_OF_MONTH, 2);
			return retorno= calendario.getTime();		
		}else if (dia==5) {//se for sabado acresceta tres dias. 
			calendario.add(Calendar.DAY_OF_MONTH, 3);
			return retorno= calendario.getTime();
		}
		
		calendario.add(Calendar.DAY_OF_MONTH, 1);
		return retorno= calendario.getTime();
	}

	public void setRetorno(Date retorno) {
		this.retorno = retorno;
	}
	
	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}

}
