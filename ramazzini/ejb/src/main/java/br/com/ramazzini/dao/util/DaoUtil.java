package br.com.ramazzini.dao.util;

import java.text.Normalizer;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.Order;

import br.com.ramazzini.model.credenciado.Credenciado;
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
		usuarioLogado = entityManager.find(Usuario.class, usuarioLogado.getId());
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
	 * Recuperar por nome ignorando acento e maiuscula e minuscula
	 * 
	 * @return entidade
	 */
	@SuppressWarnings("unchecked")
	public static final List recuperarPorNome(EntityManager entityManager, Class classePersistente,
			String nome) {
		String nomeTabela = classePersistente.getSimpleName();
		nome = removeAcentos(nome);
		String sql = "SELECT c.* FROM "+nomeTabela+" c "+  
	            "WHERE lower(sem_acento(c.nm_"+nomeTabela+")) LIKE '%"+nome.toLowerCase()+"%' ";  
	    Query query = entityManager.createNativeQuery(sql, classePersistente);  
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	protected static final String removeAcentos(String nome) {
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		return nome;
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
