package br.com.ramazzini.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.model.util.AbstractEntidade;

/**
 * Classe utilitária para ser utilizada para qualquer tipo de entidade.
 * 
 */
public class DaoUtil {

	private DaoUtil() {
	}
	
	/**
	 * Método responsável por remover a entidade passada como parâmetro.
	 * 
	 * @param entidade
	 *            entidade
	 */
	public static void remover(EntityManager entityManager, Object entidade, Long id) {
		entityManager.remove(entityManager.getReference(entidade.getClass(), id));
		entityManager.flush();
	}

	/**
	 * Classe que realiza o salvamento da entidade passada como parâmetro.
	 */
	public static final AbstractEntidade salvar(EntityManager entityManager, AbstractEntidade entidade, Usuario usuarioLogado) {
		if (entidade.getId() == null) {
			//entidade.setUsuarioInclusao(usuarioLogado);
			entityManager.persist(entidade);
		} else {
			//entidade.setUsuarioAlteracao(usuarioLogado);
			entidade = entityManager.merge(entidade);
		}
		return entidade;
	}

	/**
	 * Recupera a entidade de acordo com o id passado como parametro.
	 * @return entidade
	 */
	@SuppressWarnings("unchecked")
	public static Object recuperarPorId(EntityManager entityManager, Class classePersistente, Long id) {
		return entityManager.find(classePersistente, id);
	}

	/**
	 * Recuperar Por Id For Update.
	 * 
	 * @param entityManager
	 * @param classePersistente
	 * @param id
	 * @return Object
	 */
	@SuppressWarnings("unchecked")
	public static Object recuperarPorIdForUpdate(EntityManager entityManager, Class classePersistente, Long id) {
		final Object object = entityManager.find(classePersistente, id);
		entityManager.lock(object, LockModeType.READ);

		return object;
	}
}
