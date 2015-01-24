package br.com.ramazzini.dao.profissional;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.profissional.Profissional;


public class ProfissionalDao extends AbstractDao<Profissional> {

	private static final String QUERY_RECUPERAR_POR_NOME = "Profissional.recuperarPorNome";
	private static final String QUERY_RECUPERAR_POR_PAPEL_PROFISSIONAL = "Profissional.recuperarPorPapelProfissional";
	
	@SuppressWarnings("unchecked")
	public List<Profissional> recuperarPorNome(String nome) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", "%"+nome+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<Profissional> recuperarPorPapelProfissional(String papelProfissional, boolean ativo) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_PAPEL_PROFISSIONAL);
		query.setParameter("papelProfissional", papelProfissional);
		query.setParameter("ativo", ativo);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}