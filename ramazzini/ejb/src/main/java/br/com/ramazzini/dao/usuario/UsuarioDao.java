package br.com.ramazzini.dao.usuario;

import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.usuario.Usuario;


public class UsuarioDao extends AbstractDao<Usuario> {

	private static final String QUERY_RECUPERAR_POR_LOGIN = "Usuario.recuperarPorLogin";
	
	/**
	 * Recupera o usuário pelo login
	 * @param login
	 * @return Usuario
	 */
	@SuppressWarnings("unchecked")
	public Usuario recuperarPorLogin(String login) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_LOGIN);
		query.setParameter("login", login);
		return (Usuario) query.getSingleResult();
	}
}
