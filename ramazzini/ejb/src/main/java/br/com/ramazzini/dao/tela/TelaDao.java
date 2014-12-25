package br.com.ramazzini.dao.tela;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.tela.Tela;


public class TelaDao extends AbstractDao<Tela> {

	private static final String QUERY_RECUPERAR_POR_MDOULO_TELA = "Tela.recuperarPorModuloTela";
	private static final String QUERY_RECUPERAR_TELAS_NAO_PUBLICAS = "Tela.recuperarTelasNaoPublicas";
	
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
	
	@SuppressWarnings("unchecked")
	public List<Tela> recuperarTelasNaoPublicas(String... orderBy) {
		Query query = createNamedQuery(QUERY_RECUPERAR_TELAS_NAO_PUBLICAS);
		String order = "";
		String virgula = "";
		for (String ord : orderBy) {
			order = order + ord + virgula;
			virgula = ",";
		}		
		query.setParameter("order", order);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}