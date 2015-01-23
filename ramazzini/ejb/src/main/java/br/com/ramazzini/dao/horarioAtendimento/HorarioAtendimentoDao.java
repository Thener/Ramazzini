package br.com.ramazzini.dao.horarioAtendimento;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.horarioAtendimento.HorarioAtendimento;


public class HorarioAtendimentoDao extends AbstractDao<HorarioAtendimento> {

	private static final String QUERY_RECUPERAR_POR_NOME = "HorarioAtendimento.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<HorarioAtendimento> recuperarPorNome(String nome) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", "%"+nome+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}