package br.com.ramazzini.dao.perfilTela;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.perfilTela.PerfilTela;


public class PerfilTelaDao extends AbstractDao<PerfilTela> {

	private static final String QUERY_RECUPERAR_POR_PERFIL = "PerfilTela.recuperarPorPerfil";
	private static final String QUERY_RECUPERAR_TUDO_POR_ID = "PerfilTela.recuperarTudoPorId";
		
	@SuppressWarnings("unchecked")
	public List<PerfilTela> recuperarPorPerfil(Perfil perfil) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_PERFIL);
		query.setParameter("perfil", perfil);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@Override
	public void remover(PerfilTela perfilTela, Long id) {
		removerPorId(perfilTela, id);
	}

	public PerfilTela recuperarTudoPorId(Long id) {
		Query query = createNamedQuery(QUERY_RECUPERAR_TUDO_POR_ID);
		query.setParameter("id", id);
		try {
			return (PerfilTela) query.getSingleResult();
		} catch (NoResultException n) {
			return null;
		}
	}	
}