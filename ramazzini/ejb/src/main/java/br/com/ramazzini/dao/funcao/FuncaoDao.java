package br.com.ramazzini.dao.funcao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;


public class FuncaoDao extends AbstractDao<Funcao> {

	private static final String QUERY_RECUPERAR_POR_EMPRESA = "Funcao.recuperarPorEmpresa";
	private static final String QUERY_RECUPERAR_POR_NOME = "Funcao.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<Funcao> recuperarPorEmpresa(Empresa empresa) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_EMPRESA);
		query.setParameter("empresa", empresa);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcao> recuperarPorNome(Empresa empresa, String nomeFuncao) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("empresa", empresa);
		query.setParameter("nomeFuncao", "%"+nomeFuncao+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}