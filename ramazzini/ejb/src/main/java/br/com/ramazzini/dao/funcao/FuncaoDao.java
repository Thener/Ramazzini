package br.com.ramazzini.dao.funcao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.model.riscoOcupacional.RiscoOcupacional;


public class FuncaoDao extends AbstractDao<Funcao> {

	private static final String QUERY_EXISTE_RISCO_ERGONOMICO = "Funcao.existeRiscoErgonomico";
	private static final String QUERY_REALIZA_PROCEDIMENTOS = "Funcao.realizaProcedimentos";
	private static final String QUERY_RECUPERAR_POR_EMPRESA = "Funcao.recuperarPorEmpresa";
	private static final String QUERY_RECUPERAR_POR_NOME_EMPRESA = "Funcao.recuperarPorNomeEmpresa";
	private static final String QUERY_RECUPERAR_POR_NOME = "Funcao.recuperarPorNome";
	private static final String QUERY_RECUPERAR_PROCEDIMENTOS_POR_FUNCAO = "Funcao.recuperarProcedimentosPorFuncao";
	private static final String QUERY_RECUPERAR_RISCOS_OCUPACIONAIS = "Funcao.recuperarRiscosOcupacionais";
	
	public boolean existeRiscoErgonomico(Funcao funcao) {
		Query query = createNamedQuery(QUERY_EXISTE_RISCO_ERGONOMICO);
		query.setParameter("funcao", funcao);
		query.setMaxResults(1);
		try {
			return (query.getResultList().size() == 1) ? true: false;
		} catch (NoResultException nr) {
			return false;
		}
	}
	
	public boolean realizaProcedimentos(Funcao funcao, TipoExameClinico tipoExameClinico) {
		Query query = createNamedQuery(QUERY_REALIZA_PROCEDIMENTOS);
		query.setParameter("funcao", funcao);
		query.setParameter("tipoExameClinico", tipoExameClinico.getValue());
		query.setMaxResults(1);
		try {
			return (query.getResultList().size() == 1) ? true: false;
		} catch (NoResultException nr) {
			return false;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<Funcao> recuperarPorEmpresa(Empresa empresa) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_EMPRESA);
		query.setParameter("empresa", empresa);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcao> recuperarPorNomeEmpresa(Empresa empresa, String nomeFuncao) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME_EMPRESA);
		query.setParameter("empresa", empresa);
		query.setParameter("nomeFuncao", "%"+nomeFuncao+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<Funcao> recuperarPorNome(String nomeFuncao) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nomeFuncao", "%"+nomeFuncao+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Procedimento> recuperarProcedimentosPor(Funcao funcao) {
		Query query = createNamedQuery(QUERY_RECUPERAR_PROCEDIMENTOS_POR_FUNCAO);
		query.setParameter("funcao", funcao);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RiscoOcupacional> recuperarRiscosOcupacionais(Funcao funcao) {
		Query query = createNamedQuery(QUERY_RECUPERAR_RISCOS_OCUPACIONAIS);
		query.setParameter("funcao", funcao);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}