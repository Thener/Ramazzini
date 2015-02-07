package br.com.ramazzini.dao.avaliacaoClinica;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.funcionario.Funcionario;


public class AvaliacaoClinicaDao extends AbstractDao<AvaliacaoClinica> {

	private static final String QUERY_RECUPERAR_POR_FUNCIONARIO = "AvaliacaoClinica.recuperarPorFuncionario";
	
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
	
		
}