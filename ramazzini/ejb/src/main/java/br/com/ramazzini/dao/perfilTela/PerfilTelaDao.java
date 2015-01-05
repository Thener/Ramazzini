package br.com.ramazzini.dao.perfilTela;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.modulo.Modulo;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.perfilTela.PerfilTela;


public class PerfilTelaDao extends AbstractDao<PerfilTela> {

	private static final String QUERY_RECUPERAR_POR_PERFIL = "PerfilTela.recuperarPorPerfil";
	private static final String QUERY_RECUPERAR_TUDO_POR_ID = "PerfilTela.recuperarTudoPorId";
	private static final String QUERY_RECUPERAR_POR_PERFIL_E_MODULO = "PerfilTela.recuperarPorPerfilModulo";
		
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
	
	public boolean removerEmCascata(PerfilTela perfilTela) {
		
		int registros = 0;
		// Removendo filhos:
		Query q = getEntityManager().createNativeQuery(
				"DELETE FROM perfil_tela_acao WHERE cd_perfil_tela = " + perfilTela.getId());
		registros = q.executeUpdate();
		
		// Removendo pai:
		q = getEntityManager().createNativeQuery(
				"DELETE FROM perfil_tela WHERE cd_perfil_tela = " + perfilTela.getId());
		registros += q.executeUpdate();
		
		return (registros > 0) ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public List<PerfilTela> recuperarPorPerfilModulo(Perfil perfil, Modulo modulo) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_PERFIL_E_MODULO);
		query.setParameter("perfil", perfil);
		query.setParameter("modulo", modulo);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
}