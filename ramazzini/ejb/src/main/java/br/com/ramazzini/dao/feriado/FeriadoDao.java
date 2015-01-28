package br.com.ramazzini.dao.feriado;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.feriado.Feriado;


public class FeriadoDao extends AbstractDao<Feriado> {

	private static final String QUERY_RECUPERAR_POR_NOME = "Feriado.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<Feriado> recuperarPorNome(String nome) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", "%"+nome+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
		
}