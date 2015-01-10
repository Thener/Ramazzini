package br.com.ramazzini.dao.procedimento;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.procedimento.Procedimento;


public class ProcedimentoDao extends AbstractDao<Procedimento> {

	private static final String QUERY_RECUPERAR_POR_NOME = "Procedimento.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<Procedimento> recuperarPorNome(String nome) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", "%"+nome+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}