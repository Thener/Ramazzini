package br.com.ramazzini.dao.anamnese;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.anamnese.Anamnese;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.profissional.Profissional;


public class AnamneseDao extends AbstractDao<Anamnese> {

	private static final String QUERY_RECUPERAR_POR_AVALIACAO_CLINICA = 
			"Anamnese.recuperarPorAvaliacaoClinica";	
	private static final String QUERY_RECUPERAR_ANAMNESE_EM_ANDAMENTO_POR_AVCLINICA_MEDICO = 
			"Anamnese.recuperarAnamneseEmAndamentoPorAvClinicaMedico";
	private static final String QUERY_RECUPERAR_ANAMNESE_ANTERIOR_POR_DATA_REALIZACAO = 
			"Anamnese.recuperarAnamneseAnteriorPorDataRealizacao";	
	private static final String QUERY_RECUPERAR_ANAMNESE_ANTERIOR_POR_ANAMNESE = 
			"Anamnese.recuperarAnamneseAnteriorPorAnamnese";	
	
	@SuppressWarnings("unchecked")
	public List<Anamnese> recuperarPor(AvaliacaoClinica avaliacaoClinica) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_AVALIACAO_CLINICA);
		query.setParameter("avaliacaoClinica", avaliacaoClinica);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
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
	
	public Anamnese recuperarAnamneseAnteriorPor(Funcionario funcionario, Date dataRealizacao) {
		Query query = createNamedQuery(QUERY_RECUPERAR_ANAMNESE_ANTERIOR_POR_DATA_REALIZACAO);
		query.setParameter("funcionario", funcionario);
		query.setParameter("dataRealizacao", dataRealizacao);
		query.setMaxResults(1);
		try {
			return (Anamnese) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	public Anamnese recuperarAnamneseAnteriorPor(Funcionario funcionario, Anamnese anamnese) {
		Query query = createNamedQuery(QUERY_RECUPERAR_ANAMNESE_ANTERIOR_POR_ANAMNESE);
		query.setParameter("funcionario", funcionario);
		query.setParameter("idAnamnese", anamnese.getId());
		query.setMaxResults(1);
		try {
			return (Anamnese) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		}
	}	
}