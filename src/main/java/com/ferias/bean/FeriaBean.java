
package com.ferias.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.omnifaces.util.Messages;

import com.ferias.dao.FeriaDao;
import com.ferias.dao.FuncionarioDAO;
import com.ferias.domain.Feria;
import com.ferias.domain.Funcionario;

/**
 * Classe responsável por gerenciar a visão do usuário de forma dinamica.  
 * @author Ilailson Rocha
 *
 *@see Feria
 *@see FeriaDao
 *@see Funcionario
 *@see FuncionarioDAO
 */
@ManagedBean
@ViewScoped
public class FeriaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Feria feria;
	private List<Feria> list = new ArrayList<Feria>();
	private List<Feria> filter = new ArrayList<Feria>();
	private Date pinicial;
	
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>(); //responsável por exibir ou salvar um funcionário  que esta em outra tabela.
	
	
	public Feria getFeria() {
		return feria;
	}
	public void setFeria(Feria feria) {
		this.feria = feria;
	}
	public List<Feria> getList() {
		return list;
	}
	public void setList(List<Feria> list) {
		this.list = list;
	}
	public List<Feria> getFilter() {
		return filter;
	}
	public void setFilter(List<Feria> filter) {
		this.filter = filter;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public Date getPinicial() {
		return pinicial;
	}
	
	public void setPinicial(Date pinicial) {
		this.pinicial = pinicial;
	}
	
	//==============================OPERACIONAL==========================================
	
	@PostConstruct
	public void novo() {
		feria = new Feria();
		
		/*
		 * Essas duas definições é necessária para  quando abrir o formulário 
		 * carregar os dados da tabela funcionário
		 */
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();
		
		listar();
		
	}
	
	/**
	 * Listar todos os dados da tabela feriado incluindo de outras tabelas
	 * que estejam relacionadas. 
	 */
	public void listar() {
		FeriaDao dao = new FeriaDao();
		list = dao.listar();
	}
	
	/**
	 * Responsável por salvar uma féria de um funcionário. 
	 */
	public void salvar() {
		try 
		{
			FeriaDao dao = new FeriaDao();
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();//chamado o funcionariodao
			funcionarios = funcionarioDAO.listar(); //mostrar os funcionários que serão salvo o agendamento
			
			feria =dao.merge(feria); //salvando a feria com o funcionario
			
			
			list = dao.listar();//atualizando a tabela
			
			Messages.addGlobalInfo("Férias salva com sucesso");
		} 
		catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar Salvar");
		}
	}

	public void excluirForm() {
		try 
		{
			FeriaDao dao = new FeriaDao();
			dao.excluir(feria);
			
			list = dao.listar();
			Messages.addFlashGlobalInfo("Férias excluída com sucesso");
			
			novo();
			
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao  tentar remover a pessoa:  "+e.getMessage());
		}
	}
	
	
	
	public void excluir(ActionEvent evento) {
		try 
		{
			feria = (Feria) evento.getComponent().getAttributes().get("linhaSelecionado");
			
			FeriaDao dao = new FeriaDao();
			dao.excluir(feria);
			
			list = dao.listar();
			
			Messages.addGlobalInfo("Removido com sucesso");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar excluir "+ e.getMessage() );
		}
	}

	
	
public void editar(ActionEvent evento) {
	try 
	{
		feria = (Feria) evento.getComponent().getAttributes().get("linhaSelecionado");
		
		/*Como na hora de editar se for o caso tem que alterar o funcionário.
		 * será necessário mostrar através destas duas linhas abaixo:
		 * 
		 */
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios=funcionarioDAO.listar();
		
		
	} catch (Exception e) {
		e.printStackTrace();
		Messages.addGlobalError("Erro ao tentar selecionar uma feria");
	}
}


public void diaSeleciona(ValueChangeEvent event) {
	
	this.pinicial= (Date) event.getNewValue();
	
	System.out.println("Novo valor: "+event.getNewValue());
	
	this.feria.setpFinal((Date) event.getNewValue());
}
}
