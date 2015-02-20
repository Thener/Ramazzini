package br.com.ramazzini.dao.avaliacaoClinica;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;


public class AvaliacaoClinicaDao extends AbstractDao<AvaliacaoClinica> {

	private static final String QUERY_RECUPERAR_AV_CLINICA_EM_ANDAMENTO_POR_FUNCIONARIO = 
			"AvaliacaoClinica.recuperarAvaliacaoClinicaEmAndamentoPorFuncionario";
	private static final String QUERY_RECUPERAR_POR_FUNCIONARIO = "AvaliacaoClinica.recuperarPorFuncionario";
	private static final String QUERY_RECUPERAR_PROCEDIMENTOS_POR_AVALIACAO_CLINICA = 
		"AvaliacaoClinica.recuperarProcedimentosPorAvaliacaoClinica";
	private static final String QUERY_RECUPERAR_ULTIMA_VALIDA_POR_FUNCIONARIO = "AvaliacaoClinica.recuperarUltimaValidaPorFuncionario";
	
	public AvaliacaoClinica recuperarAvaliacaoClinicaEmAndamentoPor(Funcionario funcionario) {
		Query query = createNamedQuery(QUERY_RECUPERAR_AV_CLINICA_EM_ANDAMENTO_POR_FUNCIONARIO);
		query.setParameter("funcionario", funcionario);
		query.setMaxResults(1);
		try {
			return (AvaliacaoClinica) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<AvaliacaoClinica> recuperarPorFuncionario(Funcionario funcionario) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_FUNCIONARIO);
		query.setParameter("funcionario", funcionario);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Procedimento> recuperarProcedimentosPor(AvaliacaoClinica avaliacaoClinica) {
		Query query = createNamedQuery(QUERY_RECUPERAR_PROCEDIMENTOS_POR_AVALIACAO_CLINICA);
		query.setParameter("avaliacaoClinica", avaliacaoClinica);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	public AvaliacaoClinica recuperarUltimaValidaPorFuncionario(Funcionario funcionario) {
		Query query = createNamedQuery(QUERY_RECUPERAR_ULTIMA_VALIDA_POR_FUNCIONARIO);
		query.setParameter("funcionario", funcionario);
		query.setMaxResults(1);
		try {
			return (AvaliacaoClinica) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		}
	}	
}