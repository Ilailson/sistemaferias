package br.ferias.testJunit;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.junit.Test;

import com.ferias.domain.Feria;
import com.ferias.domain.Funcionario;
import com.ferias.util.HibernateUtil;

public class ConsultaComJPA {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ferias");
	EntityManager em = emf.createEntityManager();
	
	@Test
	public void consulta() {
		
		System.out.println("Conectou");

		String query = "select count(f) from Feria f where mes = 'Junho'";

		TypedQuery<Long> consulta = em.createQuery(query, Long.class);

		Long numeroMes = consulta.getSingleResult();

		System.out.println("O resultado é: " + numeroMes);

		em.close();

	}

	@Test
	public void outraConsulta() {
		
		System.out.println("Conectou");

		String query = "select count(f) from Feria f join f.funcionario "
				+ "where f.funcionario.equipe = 'Telecab' and '2019-08-01' between f.pInicial and f.pFinal";

		TypedQuery<Long> consulta = em.createQuery(query, Long.class);

		Long numeroMes = consulta.getSingleResult();

		System.out.println("O resultado é: " + numeroMes);

		em.close();

		// select count(fer.codigo) ferias
		// from feria fer, funcionario func
		// where fer.funcionario_codigo = func.codigo
		// and '2019-06-10' between fer.pInicial and fer.pFinal
		// and func.equipe = 'Central';
	}	
	
}

	
