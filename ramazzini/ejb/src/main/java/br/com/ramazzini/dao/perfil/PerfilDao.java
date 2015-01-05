package br.com.ramazzini.dao.perfil;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.perfilTela.PerfilTela;
import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.model.usuario.Usuario;


public class PerfilDao extends AbstractDao<Perfil> {

	private static final String QUERY_RECUPERAR_TUDO_POR_USUARIO = "Perfil.recuperarTudoPorUsuario";
	private static final String QUERY_RECUPERAR_TELAS_POR_PERFIL = "Perfil.recuperarTelasPorPerfil";
	private static final String QUERY_RECUPERAR_PERFIL_TELA_POR_PERFIL = "Perfil.recuperarPerfilTelaPorPerfil";
	private static final String QUERY_RECUPERAR_PERFIL_DISPONIVEL_POR_USUARIO = "Perfil.recuperarPerfisDisponiveisPorUsuario";
	
	
	@SuppressWarnings("unchecked")
	public List<Perfil> recuperarTudoPorUsuario(Usuario usuario) {
		Query query = createNamedQuery(QUERY_RECUPERAR_TUDO_POR_USUARIO);
		query.setParameter("usuario", usuario);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Tela> recuperarTelasPorPerfil(Perfil perfil) {
		Query query = createNamedQuery(QUERY_RECUPERAR_TELAS_POR_PERFIL);
		query.setParameter("perfil", perfil);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<PerfilTela> recuperarPerfilTelaPor(Perfil perfil) {
		Query query = createNamedQuery(QUERY_RECUPERAR_PERFIL_TELA_POR_PERFIL);
		query.setParameter("perfil", perfil);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<Perfil> recuperarPerfisDisponiveisPorUsuario(Usuario usuario) {
		Query query = createNamedQuery(QUERY_RECUPERAR_PERFIL_DISPONIVEL_POR_USUARIO);
		query.setParameter("usuario", usuario);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
}