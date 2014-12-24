package br.com.ramazzini.dao.tela;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.tela.Tela;


public class TelaDao extends AbstractDao<Tela> {

	private static final String QUERY_RECUPERAR_POR_MDOULO_TELA = "Tela.recuperarPorModuloTela";
	
	public Tela recuperarPorModuloTela(String nomeModulo, String nomeTela) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_MDOULO_TELA);
		query.setParameter("nomeModulo", nomeModulo);
		query.setParameter("nomeTela", nomeTela);
		try {
			return (Tela) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		}
	}
		
}