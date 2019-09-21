package br.ferias.testJunit;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;

import com.ferias.domain.Feria;
import com.ferias.domain.Funcionario;
import com.ferias.util.HibernateUtil;

public class Consultas {

	@Test
	public void testeConsultas() {
		
		Feria feria = new Feria();
		Funcionario funcionario= new Funcionario();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		
		Criteria count = 
		sessao.createCriteria("select count(feria.codigo) from Feria feria, Funcionario funcionario where funcionario.equipe ="+ funcionario.getEquipe() 
		+ "and "+feria.getpInicial()+
		" between feria.pInicial and feria.pFinal group by codigo");
		
		System.out.println(count);
		System.out.println("Funcionou");
	}
	
}
