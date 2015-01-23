package br.com.ramazzini.dao.procedimentoCredenciado;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimentoCredenciado.ProcedimentoCredenciado;


public class ProcedimentoCredenciadoDao extends AbstractDao<ProcedimentoCredenciado> {
	
	private static final String QUERY_RECUPERAR_POR_CREDENCIADO = "ProcedimentoCredenciado.recuperarPorCredenciado";
	private static final String QUERY_RECUPERAR_POR_PROCEDIMENTO = "ProcedimentoCredenciado.recuperarPorProcedimento";
	private static final String QUERY_RECUPERAR_POR_NOME_PROCEDIMENTO = "ProcedimentoCredenciado.recuperarPorNomeProcedimento";
	private static final String QUERY_RECUPERAR_POR_NOME_CREDENCIADO = "ProcedimentoCredenciado.recuperarPorNomeCredenciado";
	
	@SuppressWarnings("unchecked")
	public List<ProcedimentoCredenciado> recuperarPor(Credenciado credenciado) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_CREDENCIADO);
		query.setParameter("credenciado", credenciado);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcedimentoCredenciado> recuperarPor(Procedimento procedimento) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_PROCEDIMENTO);
		query.setParameter("procedimento", procedimento);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcedimentoCredenciado> recuperarPorNome(Credenciado credenciado, String nomeProcedimento) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME_PROCEDIMENTO);
		query.setParameter("credenciado", credenciado);
		query.setParameter("nomeProcedimento", "%"+nomeProcedimento+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}		
	
	@SuppressWarnings("unchecked")
	public List<ProcedimentoCredenciado> recuperarPorNome(Procedimento procedimento, String nomeCredenciado) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME_CREDENCIADO);
		query.setParameter("procedimento", procedimento);
		query.setParameter("nomeCredenciado", "%"+nomeCredenciado+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}		
}