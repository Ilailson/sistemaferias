package com.ferias.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ferias.domain.Feria;

/**
 * Classe com os métodos de crud.
 * 
 * @author Ilailson Rocha
 * @author André Evan
 *
 * @see FeriaBean
 * 
 */
public class FeriaDao extends GenericDAO<Feria> {

	
	/**
	 * Método que checa no banco de dados quantos colaboradores por equipe (tabela equipe) podem 
	 * entrar de férias através do atributo quantidade. <p>
	 * 
	 * Checa no banco de dados se: <br>
	 * Período inicial escolhido na tela está entre o período inicial e periodo final ou <br>
	 * Período final escolhido na tela está entre o período inicial e periodo final <br>
	 * 
	 * @author André Evan
	 */
	public boolean validarFerias(Feria feria) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ferias");
		EntityManager em = emf.createEntityManager();
		
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT count(*) FROM Feria fe ");
		sb.append("join fe.funcionario fu ");
		sb.append("where fu.equipe.codigo = :equipe ");
		sb.append("and ( ");
		sb.append("(:dataInicio between fe.pInicial and fe.pFinal) ");
		sb.append("or (:dataFinal between fe.pInicial and fe.pFinal) ");
		sb.append("or (fe.pInicial between :dataInicio and :dataFinal) ");
		sb.append("or (fe.pFinal between :dataInicio and :dataFinal))");
		

		Query query = em.createQuery(sb.toString());
		query.setParameter("equipe", feria.getFuncionario().getEquipe().getCodigo());
		query.setParameter("dataInicio", feria.getpInicial());
		query.setParameter("dataFinal", feria.getpFinal());
		
		System.out.println("Quantidade possivel da equipe:  "+ feria.getFuncionario().getEquipe().getQuantidade_ferias());
		System.out.println("Quantidade encontrada "+ new Long((long) query.getSingleResult()));
		if (feria.getFuncionario().getEquipe().getQuantidade_ferias() 
				> new Long((long) query.getSingleResult())) {
			return true;
		}
		
		return false;
	}

	
}
