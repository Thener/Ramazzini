package br.com.ramazzini.dao.anamnese;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.anamnese.Anamnese;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.profissional.Profissional;


public class AnamneseDao extends AbstractDao<Anamnese> {

	private static final String QUERY_RECUPERAR_ANAMNESE_EM_ANDAMENTO_POR_AVCLINICA_MEDICO = 
			"Anamnese.recuperarAnamneseEmAndamentoPorAvClinicaMedico";
	
	public Anamnese recuperarAnamneseEmAndamentoPor(AvaliacaoClinica avaliacaoClinica, Profissional medico) {
		Query query = createNamedQuery(QUERY_RECUPERAR_ANAMNESE_EM_ANDAMENTO_POR_AVCLINICA_MEDICO);
		query.setParameter("avaliacaoClinica", avaliacaoClinica);
		query.setParameter("medico", medico);
		query.setMaxResults(1);
		try {
			return (Anamnese) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		}
	}
}