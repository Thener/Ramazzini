package br.com.ramazzini.dao.modulo;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.modulo.Modulo;
import br.com.ramazzini.model.perfil.Perfil;


public class ModuloDao extends AbstractDao<Modulo> {

	private static final String QUERY_RECUPERAR_POR_PERFIL = "Modulo.recuperarPorPerfil";
	
	@SuppressWarnings("unchecked")
	public List<Modulo> recuperarPorPerfil(Perfil perfil) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_PERFIL);
		query.setParameter("perfil", perfil);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}