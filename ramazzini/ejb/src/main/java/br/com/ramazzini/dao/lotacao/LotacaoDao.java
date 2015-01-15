package br.com.ramazzini.dao.lotacao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.lotacao.Lotacao;


public class LotacaoDao extends AbstractDao<Lotacao> {

	private static final String QUERY_RECUPERAR_POR_EMPRESA = "Lotacao.recuperarPorEmpresa";
	
	@SuppressWarnings("unchecked")
	public List<Lotacao> recuperarPorEmpresa(Empresa empresa) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_EMPRESA);
		query.setParameter("empresa", empresa);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}