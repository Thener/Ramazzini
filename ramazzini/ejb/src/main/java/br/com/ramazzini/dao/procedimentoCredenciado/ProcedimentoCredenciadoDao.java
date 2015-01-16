package br.com.ramazzini.dao.procedimentoCredenciado;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.model.procedimentoCredenciado.ProcedimentoCredenciado;


public class ProcedimentoCredenciadoDao extends AbstractDao<ProcedimentoCredenciado> {
	
	private static final String QUERY_RECUPERAR_POR_CREDENCIADO = "ProcedimentoCredenciado.recuperarPorCredenciado";
	private static final String QUERY_RECUPERAR_POR_NOME = "ProcedimentoCredenciado.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<ProcedimentoCredenciado> recuperarPorCredenciado(Credenciado credenciado) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_CREDENCIADO);
		query.setParameter("credenciado", credenciado);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcedimentoCredenciado> recuperarPorNome(Credenciado credenciado, String nomeProcedimento) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("credenciado", credenciado);
		query.setParameter("nomeProcedimento", "%"+nomeProcedimento+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}			
}