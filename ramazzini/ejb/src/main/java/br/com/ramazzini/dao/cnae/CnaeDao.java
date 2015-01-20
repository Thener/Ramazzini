package br.com.ramazzini.dao.cnae;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.cnae.Cnae;


public class CnaeDao extends AbstractDao<Cnae> {

private static final String QUERY_RECUPERAR_POR_NUMERO = "Cnae.recuperarPorNumero";
	
	@SuppressWarnings("unchecked")
	public List<Cnae> recuperarPorNumero(String numero) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NUMERO);
		query.setParameter("numero", "%"+numero+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}		
}