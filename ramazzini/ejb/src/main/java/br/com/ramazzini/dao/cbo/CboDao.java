package br.com.ramazzini.dao.cbo;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.cbo.Cbo;


public class CboDao extends AbstractDao<Cbo> {

private static final String QUERY_RECUPERAR_POR_NUMERO = "Cbo.recuperarPorNumero";
	
	@SuppressWarnings("unchecked")
	public List<Cbo> recuperarPorNumero(String numero) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NUMERO);
		query.setParameter("numero", "%"+numero+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}		
}