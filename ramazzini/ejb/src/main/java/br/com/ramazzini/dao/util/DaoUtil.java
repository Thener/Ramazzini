package br.com.ramazzini.dao.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.criteria.Order;

import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.model.util.AbstractEntidade;
import br.com.ramazzini.util.TimeFactory;

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
		usuarioLogado = entityManager.find(Usuario.class, usuarioLogado.getId());
		if (entidade.getId() == null) {
			entidade.setUsuarioInclusao(usuarioLogado);
			entidade.setDataInclusao(TimeFactory.createDataHora());
			entityManager.persist(entidade);
		} else {
			entidade.setUsuarioAlteracao(usuarioLogado);
			entidade.setDataAlteracao(TimeFactory.createDataHora());
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
	
	/**
	 * Recupera todas as entidade do tipo T.
	 * 
	 * @return entidade
	 */
	@SuppressWarnings("unchecked")
	public static final List recuperarTodos(EntityManager entityManager, Class classePersistente,
			Order... ordenacoes) {
		List retorno = null;
		Query q = entityManager.createQuery("SELECT c FROM " + classePersistente.getSimpleName() + " c "
				+ adicionaOrderByHql(ordenacoes));		
		retorno = q.getResultList();		
		return retorno;
	}
	
	/**
	 * Adiciona o orderBy no final da query a ser utilizada.
	 * 
	 * @param ordenacoes
	 *            a serem utilizadas para a busca
	 * @return string com o orderBy
	 */
	public static final String adicionaOrderByHql(Order... ordenacoes) {
		StringBuilder builder = new StringBuilder();
		if (ordenacoes != null && ordenacoes.length > 0) {
			builder.append(" order by ");
			for (int i = 0; i < ordenacoes.length - 1; i++) {
				builder.append(ordenacoes[i].toString());
				builder.append(", ");
			}
			builder.append(ordenacoes[ordenacoes.length - 1]);
			builder.toString();
		}

		return builder.toString();
	}
}
