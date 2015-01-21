package br.com.ramazzini.dao.setor;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.setor.Setor;


public class SetorDao extends AbstractDao<Setor> {

	private static final String QUERY_RECUPERAR_POR_EMPRESA = "Setor.recuperarPorEmpresa";
	private static final String QUERY_RECUPERAR_POR_NOME = "Setor.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<Setor> recuperarPorEmpresa(Empresa empresa) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_EMPRESA);
		query.setParameter("empresa", empresa);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Setor> recuperarPorNome(Empresa empresa, String nomeSetor) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("empresa", empresa);
		query.setParameter("nomeSetor", "%"+nomeSetor+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}