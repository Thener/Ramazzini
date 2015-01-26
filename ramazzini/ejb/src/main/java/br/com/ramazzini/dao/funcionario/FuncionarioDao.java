package br.com.ramazzini.dao.funcionario;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcionario.Funcionario;


public class FuncionarioDao extends AbstractDao<Funcionario> {

	private static final String QUERY_RECUPERAR_POR_EMPRESA = "Funcionario.recuperarPorEmpresa";
	private static final String QUERY_RECUPERAR_POR_NOME_EMPRESA = "Funcionario.recuperarPorNomeEmpresa";
	private static final String QUERY_RECUPERAR_POR_NOME = "Funcionario.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> recuperarPorEmpresa(Empresa empresa) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_EMPRESA);
		query.setParameter("empresa", empresa);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> recuperarPorNome(Empresa empresa, String nomeFuncionario) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME_EMPRESA);
		query.setParameter("empresa", empresa);
		query.setParameter("nomeFuncionario", "%"+nomeFuncionario+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Funcionario> recuperarPorNome(String nomeFuncionario) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nomeFuncionario", "%"+nomeFuncionario+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}