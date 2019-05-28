package com.ferias.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.ferias.dao.FuncionarioDAO;
import com.ferias.domain.Funcionario;

/**
 * Responsável por realizar a interação do usuário com a tela e realizar 
 * transações de forma dinamica no banco de dados. 
 * @author Ilailson Rocha
 * 
 * @see FuncionarioDAO
 * @see Funcionario
 *
 */
@ViewScoped
@ManagedBean
public class FuncionarioBean {

	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> list = new ArrayList<Funcionario>();
	private List<Funcionario> filter =new  ArrayList<Funcionario>();//responsável por corrigir erros na hora de filtra na tabela. 
	
	
	//======================GET SET=============================================
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public List<Funcionario> getList() {
		return list;
	}
	
	public void setList(List<Funcionario> list) {
		this.list = list;
	}
	
		
	public List<Funcionario> getFilter() {
		return filter;
	}
	
	public void setFilter(List<Funcionario> filter) {
		this.filter = filter;
	}
	
	//==============================OPERACIONAL==========================================
	
	/**
	 * Salvar um novo registro ou atualizar
	 */
	public void salvar() {
		try 
		{
			
			FuncionarioDAO dao = new FuncionarioDAO();
			
			 dao.merge(funcionario);
			 
			 novo();
			 
			 Messages.addGlobalInfo("Salvo com sucesso");
			 
			 list = dao.listarOrdenacao("nome");
			 
		} catch (Exception e) {
			e.printStackTrace();
			
			Messages.addGlobalError("Erro ao tentar salvar um funcionário");
		}	
	}
	
	/**
	 * Responsável editar uma cadastro no formulário
	 * <p>
	 * 
	 * f:attribute name="pessoaSelecionada": é definido dentro do botão editar no 
	 * formulário de cadastro e após é só clicar em salvar para concluir a alteração
	 * 
	 * 
	 */
	public void editar(ActionEvent evento) {
		try 
		{
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("linhaSelecionado");
		
			
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um funconário");
		}
	}

	/**
	 * Excluir quando clicar no botão da tabela. 
	 * @param evento
	 */
	public void excluir(ActionEvent evento) {
		try 
		{
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("linhaSelecionado");
			
			FuncionarioDAO dao = new FuncionarioDAO();
			
			dao.excluir(funcionario);
			
			novo();
			
			list = dao.listar();
			
			Messages.addGlobalInfo("Funcionário removido com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao  tentar remover a pessoa:  "+e.getMessage());
		}
	}
	
	
	/**
	 * Exclui quando clicar no botão do formulário
	 */
	public void excluirForm() {
		try 
		{
			
			FuncionarioDAO dao = new FuncionarioDAO();
			
			dao.excluir(funcionario);
			
			novo();
			
			list = dao.listar();
			
			Messages.addGlobalInfo("Funcionário removido com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao  tentar remover a pessoa:  "+e.getMessage());
		}
	}
	
	
	public void novo() {
		try 
		{
			funcionario = new Funcionario();
			
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo funcionário");
		}
	}
	

	/**
	 * Carrega as informações dentro da tabela quando o formulário for chamado. 
	 */
	@PostConstruct
	public void listar() {
		try 
		{
			FuncionarioDAO dao = new FuncionarioDAO();
			
			list = dao.listar();
		} 
		catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionários");
		}
	}
	
}
