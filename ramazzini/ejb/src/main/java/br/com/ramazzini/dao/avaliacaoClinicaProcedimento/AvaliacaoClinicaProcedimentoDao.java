package br.com.ramazzini.dao.avaliacaoClinicaProcedimento;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimento;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;


public class AvaliacaoClinicaProcedimentoDao extends AbstractDao<AvaliacaoClinicaProcedimento> {

	private static final String QUERY_RECUPERAR_POR_AVALIACAO_CLINICA = "AvaliacaoClinicaProcedimento.recuperarPorAvaliacaoClinica";
	private static final String QUERY_RECUPERAR_POR_PROCEDIMENTO = "AvaliacaoClinicaProcedimento.recuperarPorProcedimento";
	private static final String QUERY_VERIFICA_VALIDADE_PROCEDIMENTO = "AvaliacaoClinicaProcedimento.verificaValidadeProcedimento";
	
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
	
	@SuppressWarnings("unchecked")
	public List<AvaliacaoClinicaProcedimento> recuperarPorProcedimento(AvaliacaoClinica avaliacaoClinica, Procedimento procedimento) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_PROCEDIMENTO);
		query.setParameter("avaliacaoClinica", avaliacaoClinica);
		query.setParameter("procedimento", procedimento);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	public boolean verificaValidadeDoProcedimento(Funcionario funcionario, Procedimento procedimento, Date dataReferencia) {
		Query query = createNamedQuery(QUERY_VERIFICA_VALIDADE_PROCEDIMENTO);
		query.setParameter("funcionario", funcionario);
		query.setParameter("procedimento", procedimento);
		query.setParameter("dataReferencia", dataReferencia);
		query.setMaxResults(1);
		try {
			return (query.getResultList().size() == 1) ? Boolean.TRUE : Boolean.FALSE;
		} catch (NoResultException nr) {
			return Boolean.FALSE;
		}
	}	
}