package br.com.ramazzini.dao.perfilTela;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.perfilTela.PerfilTela;


public class PerfilTelaDao extends AbstractDao<PerfilTela> {

	private static final String QUERY_RECUPERAR_POR_PERFIL = "PerfilTela.recuperarPorPerfil";
		
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
	
	/**
	 * Método sobrescrito em virtude da tabela PerfilTelaId (ManyToMany)
	 * @param perfilTela
	 * @return
	 */
	public boolean remover(PerfilTela perfilTela) {
		Query q = getEntityManager().createQuery("DELETE FROM " + PerfilTela.class.getSimpleName() + " WHERE id = " + perfilTela.getId());
		return (q.executeUpdate() > 0 ? true : false);
	}	
		
}