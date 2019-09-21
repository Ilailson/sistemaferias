package com.ferias.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ferias.domain.Funcionario;

/**
 * Responsável por realizar o CRUD de Funcionarios através dos métodos herdados
 * pelo GenericDAO
 * 
 * @author Ilailson Rocha
 * 
 * @see Funcionario
 * @see GenericDAO
 *
 */
public class FuncionarioDAO extends GenericDAO<Funcionario> {	
}
