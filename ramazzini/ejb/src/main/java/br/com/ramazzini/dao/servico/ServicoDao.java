package br.com.ramazzini.dao.servico;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.servico.Servico;


public class ServicoDao extends AbstractDao<Servico> {

	private static final String QUERY_RECUPERAR_POR_NOME = "Servico.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<Servico> recuperarPorNome(String nome) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", "%"+nome+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}