package br.com.ramazzini.dao.credenciado;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.model.procedimento.Procedimento;


public class CredenciadoDao extends AbstractDao<Credenciado> {

	private static final String QUERY_RECUPERAR_POR_PROCEDIMENTO = "Credenciado.recuperarPorProcedimento";
	
	@SuppressWarnings("unchecked")
	public List<Credenciado> recuperarPorProcedimento(Procedimento procedimento) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_PROCEDIMENTO);
		query.setParameter("procedimento", procedimento);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}		
		
}