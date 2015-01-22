package br.com.ramazzini.dao.funcaoProcedimento;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimento;
import br.com.ramazzini.model.procedimento.Procedimento;


public class FuncaoProcedimentoDao extends AbstractDao<FuncaoProcedimento> {

	private static final String QUERY_RECUPERAR_POR_FUNCAO = "FuncaoProcedimento.recuperarPorFuncao";
	private static final String QUERY_RECUPERAR_POR_PROCEDIMENTO = "FuncaoProcedimento.recuperarPorProcedimento";
	
	@SuppressWarnings("unchecked")
	public List<FuncaoProcedimento> recuperarPorFuncao(Funcao funcao) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_FUNCAO);
		query.setParameter("funcao", funcao);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FuncaoProcedimento> recuperarPorProcedimento(Funcao funcao, Procedimento procedimento) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_PROCEDIMENTO);
		query.setParameter("funcao", funcao);
		query.setParameter("procedimento", procedimento);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}