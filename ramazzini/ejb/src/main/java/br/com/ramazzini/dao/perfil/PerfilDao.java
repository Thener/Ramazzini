package br.com.ramazzini.dao.perfil;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.tela.Tela;


public class PerfilDao extends AbstractDao<Perfil> {

	private static final String QUERY_RECUPERAR_TELAS_POR_PERFIL = "Perfil.recuperarTelasPorPerfil";
	
	@SuppressWarnings("unchecked")
	public List<Tela> recuperarTelas(Perfil perfil) {
		Query query = createNamedQuery(QUERY_RECUPERAR_TELAS_POR_PERFIL);
		query.setParameter("perfil", perfil);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	

}