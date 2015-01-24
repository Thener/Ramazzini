package br.com.ramazzini.dao.parametro;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.parametro.Parametro;


public class ParametroDao extends AbstractDao<Parametro> {

	private static final String QUERY_RECUPERAR_POR_NOME = "Parametro.recuperarPorNome";
	
	public Parametro recuperarPorNome(String nome) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", nome);
		try {
			return (Parametro) query.getSingleResult();
		} catch (NoResultException nr) {
			Parametro p = new Parametro();
			p.setNome(nome);
			return p;
		}
	}	
		
}