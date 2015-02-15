package br.com.ramazzini.dao.funcaoProcedimento;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimento;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoExameClinico;


public class FuncaoProcedimentoDao extends AbstractDao<FuncaoProcedimento> {

	private static final String QUERY_RECUPERAR_POR_FUNCAO = "FuncaoProcedimento.recuperarPorFuncao";
	private static final String QUERY_RECUPERAR_POR_FUNCAO_PROCEDIMENTO = "FuncaoProcedimento.recuperarPorFuncaoProcedimento";
	private static final String QUERY_RECUPERAR_POR_PROCEDIMENTO = "FuncaoProcedimento.recuperarPorProcedimento";
	private static final String QUERY_RECUPERAR_PROCEDIMENTOS_DA_FUNCAO = "FuncaoProcedimento.recuperarProcedimentosDaFuncao";
	private static final String QUERY_RECUPERAR_RETORNO_POR_FUNCAO_PROCEDIMENTO_TIPOEXAMECLINICO = 
			"FuncaoProcedimento.recuperarRetornoPorFuncaoProcedimentoTipoExameClinico";
	private static final String QUERY_VERIFICAR_EXIGENCIA = "FuncaoProcedimento.verificarExigencia";
	
	public FuncaoProcedimento recuperarPor(Funcao funcao, Procedimento procedimento) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_FUNCAO_PROCEDIMENTO);
		query.setParameter("funcao", funcao);
		query.setParameter("procedimento", procedimento);
		try {
			return (FuncaoProcedimento) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
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
	
	@SuppressWarnings("unchecked")
	public List<Procedimento> recuperarProcedimentosDaFuncao(Funcao funcao, TipoExameClinico tipoExameClinico) {
		Query query = createNamedQuery(QUERY_RECUPERAR_PROCEDIMENTOS_DA_FUNCAO);
		query.setParameter("funcao", funcao);
		query.setParameter("tipoExameClinico", tipoExameClinico.getValue());
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	public Integer recuperarRetornoPor(Funcao funcao, Procedimento procedimento, TipoExameClinico tipoExameClinico) {
		Query query = createNamedQuery(QUERY_RECUPERAR_RETORNO_POR_FUNCAO_PROCEDIMENTO_TIPOEXAMECLINICO);
		query.setParameter("funcao", funcao);
		query.setParameter("procedimento", procedimento);
		try {
			FuncaoProcedimento fp = (FuncaoProcedimento) query.getSingleResult();
			Integer retorno = 0;
			if (tipoExameClinico.getValue().equals("ADM")) {
				retorno = fp.getRetornoAdmissional();
			} else if (tipoExameClinico.getValue().equals("PER")) {
				retorno = fp.getRetornoPeriodico();
			} else if (tipoExameClinico.getValue().equals("MUD")) {
				retorno = fp.getRetornoMudancaFuncao();
			} else if (tipoExameClinico.getValue().equals("RET")) {
				retorno = fp.getRetornoRetornoTrabalho();
			}
			return retorno;
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	public boolean verificarExigencia(Funcao funcao, Procedimento procedimento, TipoExameClinico tipoExameClinico) {
		Query query = createNamedQuery(QUERY_VERIFICAR_EXIGENCIA);
		query.setParameter("funcao", funcao);
		query.setParameter("procedimento", procedimento);
		query.setParameter("tipoExameClinico", tipoExameClinico.getValue());
		query.setMaxResults(1);
		try {
			return query.getResultList().size() == 1 ? Boolean.TRUE : Boolean.FALSE;
		} catch (NoResultException nr) {
			return false;
		}
	}	
}