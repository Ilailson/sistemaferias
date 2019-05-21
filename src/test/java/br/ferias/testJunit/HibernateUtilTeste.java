package br.ferias.testJunit;

import org.hibernate.Session;
import org.junit.Test;

import com.ferias.util.HibernateUtil;

/**
 * Teste para saber se a conexão com o banco esta normal. <p>
 * 
 * Obs. Ao rodar este testes criar ou atualizar informações pendentes no banco. 
 * 
 * @author Ilailson Rocha
 *
 */
public class HibernateUtilTeste {

	@Test
	public void conectar() {
		
		try {
			Session session = HibernateUtil.getFabricaDeSessoes().openSession();
			session.close();
			HibernateUtil.getFabricaDeSessoes().close();
			System.out.println("Conectado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("O erro foi: "+ e.getMessage());
		}
	}
}

/*Classe responsável por criar e altearar as classes que estão no arquivo hibernate.cfg.xml.
 * 
 */

