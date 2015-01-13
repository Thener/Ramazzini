package br.com.ramazzini.dao.credenciado;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.credenciado.Credenciado;


public class CredenciadoDao extends AbstractDao<Credenciado> {

	private static final String QUERY_RECUPERAR_POR_NOME = "Credenciado.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<Credenciado> recuperarPorNome(String nome) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", "%"+nome+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}