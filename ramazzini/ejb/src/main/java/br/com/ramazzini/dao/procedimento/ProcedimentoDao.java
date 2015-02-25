package br.com.ramazzini.dao.procedimento;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.model.procedimento.TipoProcedimento;


public class ProcedimentoDao extends AbstractDao<Procedimento> {

//	private static final String QUERY_RECUPERAR_POR_NOME = "Procedimento.recuperarPorNome";
	private static final String QUERY_RECUPERAR_POR_TIPO_PROCEDIMENTO = "Procedimento.recuperarPorTipoProcedimento";
	private static final String QUERY_RECUPERAR_POR_TIPO_EXAME_CLINICO = "Procedimento.recuperarPorTipoExameClinico";
	
//	@SuppressWarnings("unchecked")
//	public List<Procedimento> recuperarPorNome(String nome) {
//		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
//		query.setParameter("nome", "%"+nome+"%");
//		try {
//			return query.getResultList();
//		} catch (NoResultException nr) {
//			return null;
//		}
//	}	
	
	@SuppressWarnings("unchecked")
	public List<Procedimento> recuperarPorTipoProcedimento(TipoProcedimento tipoProcedimento) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_TIPO_PROCEDIMENTO);
		query.setParameter("tipoProcedimento", tipoProcedimento.getValue());
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
	
	public Procedimento recuperarPorTipoExameClinico(TipoExameClinico tipoExameClinico) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_TIPO_EXAME_CLINICO);
		query.setParameter("tipoExameClinico", tipoExameClinico.getValue());
		try {
			return (Procedimento) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}