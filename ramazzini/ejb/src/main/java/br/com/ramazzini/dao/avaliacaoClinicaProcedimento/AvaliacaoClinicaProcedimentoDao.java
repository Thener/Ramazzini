package br.com.ramazzini.dao.avaliacaoClinicaProcedimento;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimento;


public class AvaliacaoClinicaProcedimentoDao extends AbstractDao<AvaliacaoClinicaProcedimento> {

	private static final String QUERY_RECUPERAR_POR_AVALIACAO_CLINICA = "AvaliacaoClinicaProcedimento.recuperarPorAvaliacaoClinica";
	
	@SuppressWarnings("unchecked")
	public List<AvaliacaoClinicaProcedimento> recuperarPorAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_AVALIACAO_CLINICA);
		query.setParameter("avaliacaoClinica", avaliacaoClinica);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
			
}