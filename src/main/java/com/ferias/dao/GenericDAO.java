package com.ferias.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ferias.util.HibernateUtil;

/**
 * Responsável por realizar o crud de forma genérica.  
 * @author Ilailson Rocha
 *
 * @param <Entidade> Nome da classe que será passada de forma genérica
 */
public class GenericDAO<Entidade> {

	@SuppressWarnings("unused")
	/**
	 * Recebe a classe de forma genérica
	 */
	private Class<Entidade> classe;

	/**
	 * Esse construtor obrigatóriamente tem que ter esses parametros de acordo com documentação.
	 */
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	/**
	 * Responsável por salvar de forma genérica no banco de dados.  
	 * <p>
	 * 
	 * @param entidade Nome classe que será persistida no banco de dados. 
	 */
	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction trasacao = null;
		

		try {
			trasacao = sessao.beginTransaction();
			sessao.save(entidade);

			trasacao.commit();

		} catch (Exception erro) {
			if (trasacao != null) {
				trasacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	/**
	 * Responsável por salvar ou atualizar no banco de dados. Se já existir a
	 * informação irá atualizar se não irá adicionar.
	 * @param entidade
	 */
	public Entidade merge(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction trasacao = null;

		try {
			trasacao = sessao.beginTransaction();
			
			
			Entidade entsalva = (Entidade) sessao.merge(entidade);

			trasacao.commit();
			
			return entsalva;

		} catch (Exception erro) {
			if (trasacao != null) {
				trasacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	/**
	 * Retorna uma lista genérica da classe passada por parametro
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consuta = session.createCriteria(classe);
			List<Entidade> resultado = consuta.list();
			return resultado;
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			throw erro;
		} finally {
			session.close();
		}

	}
	
	/**
	 * Lista de forma ordenada  
	 * @param ordenacao
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Entidade> listarOrdenacao(String ordenacao){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria  consulta = sessao.createCriteria(classe);
			consulta.addOrder(Order.asc(ordenacao));
			List<Entidade> resultado = consulta.list();
			return resultado;
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}finally {
			sessao.close();
		}
		
		/*Método responsável por listar geralmente os combos de forma ordena. Pode ser 
		 * utilizar nos metodos de cadastrar, novo e editar.* 
		 */
	}
	

	/**
	 * Responsável por buscar informações do banco de dados através do código. 
	 * @param codigo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Entidade buscarPorCodigo(Long codigo) {
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = session.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
		} catch (Exception erro) {
			throw erro;
		} finally {
			session.close();
		}

	}

	/**
	 * Método responsável por excluir do banco de dados de forma genérica
	 * @param entidade
	 */
	public void excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);

			transacao.commit();

		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	/**
	 * Responsável por editar no banco de dados de forma genérica. Geralmente
	 * será utilizar quando se tem um dialogo separado. 
	 * @param entidade
	 */
	public void editar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;

		} finally {
			sessao.close();
		}
	}
}

/*
 * Essa classe vai servir para reaproveitamento de código. Ou seja não será mas
 * necessário repetir os quatro métodos para para cada classe DAO que cria.
 * Simplesmente será herdado. Não será mas necessário adicionar nenhuma
 * informação.
 * 
 * ex: No momento da criação da classe basta extender o GenericDAO e informar o
 * nome da classe de dominio
 * 
 * public class EstadoDAO extends GenericDAO<Estado> {}
 * 
 */

/*
 * Esse padrão abaixo e exigido na documentação na classe genérica (no
 * construtor) para para listar os itens.
 * 
 * @SuppressWarnings("unused") private Class<Entidade> classe;
 * 
 * @SuppressWarnings("unchecked") public GenericDAO() { this.classe =
 * (Class<Entidade>) ((ParameterizedType)getClass().
 * getGenericSuperclass()).getActualTypeArguments()[0];}
 * 
 */

/*
 * MERGE
 * 
 * é um método que pode tando salvar quando atualizar. Se já existe ele atualiza
 * (alterar um dado) se não salva outro
 */
