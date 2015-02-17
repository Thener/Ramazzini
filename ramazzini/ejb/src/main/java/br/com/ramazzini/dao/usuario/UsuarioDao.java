package br.com.ramazzini.dao.usuario;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.usuario.Usuario;


public class UsuarioDao extends AbstractDao<Usuario> {

	private static final String QUERY_RECUPERAR_POR_LOGIN = "Usuario.recuperarPorLogin";
	private static final String QUERY_RECUPERAR_POR_LIKE_LOGIN = "Usuario.recuperarPorLikeLogin";
	private static final String QUERY_RECUPERAR_POR_NOME = "Usuario.recuperarPorNome";
	
	/**
	 * Recupera o usuário pelo login
	 * @param login
	 * @return Usuario
	 */
	public Usuario recuperarPorLogin(String login) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_LOGIN);
		query.setParameter("login", login);
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	/**
	 * Recupera o usuário pelo login ou trecho do login
	 * @param login
	 * @return Lista de Usuario
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> recuperarPorLoginLike(String login) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_LIKE_LOGIN);
		query.setParameter("login", "%"+login+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> recuperarPorNome(String nome) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", "%"+nome+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}		
}
