package br.com.ramazzini.dao.guia;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.guia.Guia;

public class GuiaDao extends AbstractDao<Guia> {

	private static final String QUERY_RECUPERAR_POR_FUNCIONARIO_DATA_EMISSAO = "Guia.recuperarPorFuncionarioDataEmissao";
	private static final String QUERY_RECUPERAR_POR_FUNCIONARIO = "Guia.recuperarPorFuncionario";
	
	@SuppressWarnings("unchecked")
	public List<Guia> recuperarPor(Funcionario funcionario, Date dataEmissao) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_FUNCIONARIO_DATA_EMISSAO);
		query.setParameter("funcionario", funcionario);
		query.setParameter("dataEmissao", dataEmissao);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Guia> recuperarPorFuncionario(Funcionario funcionario) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_FUNCIONARIO);
		query.setParameter("funcionario", funcionario);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}

}