package br.com.ramazzini.dao.util;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ramazzini.model.Member;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.model.util.AbstractEntidade;

/**
 * Dao abstrato que implementa os métodos genéricos para acesso ao banco de
 * dados.
 *
 * @param <T>
 */
@Stateless
public abstract class AbstractDao<T extends AbstractEntidade> {
		
	private EntityManager entityManager;
	
	@Inject
    private Logger log;

	private Class<T> classePersistente = null;	
	
	public void remover(T entidade) {
		DaoUtil.remover(entityManager, entidade);
	}

	@SuppressWarnings("unchecked")
	public T salvar(T entidade, Usuario usuarioLogado) {
		return (T) DaoUtil.salvar(entityManager, entidade, usuarioLogado);
	}

	@SuppressWarnings("unchecked")
	public T recuperarPorId(Long id) {
		return (T) DaoUtil.recuperarPorId(entityManager, getClassePersistente(), id);
	}
	
	@SuppressWarnings("unchecked")
	public T recuperarPorIdForUpdate(Long id) {
		return (T) DaoUtil.recuperarPorIdForUpdate(entityManager, getClassePersistente(), id);
	}
	
	/**
	 * Método que retorna a classe persistente do tipo T.
	 * 
	 * @return classe do tipo T
	 */
	@SuppressWarnings("unchecked")
	protected final Class<T> getClassePersistente() {
		if (classePersistente == null) {
			classePersistente =
					(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return classePersistente;
	}
	
	protected Query createQuery(String query) {
		return entityManager.createQuery(query);
	}

	protected Query createNamedQuery(String query) {
		return entityManager.createNamedQuery(query);
	}

	public void clear() {
		entityManager.clear();
	}	
	
	/**
	 * Atualiza a entidade com os valores atuais do banco de dados.
	 * 
	 * @param entidade
	 */
	public void refresh(AbstractEntidade entidade) {
		entityManager.refresh(entidade);
	}
	
	/**
	 * Fecha o conjunto de resultados.
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	protected void closeResultSet(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			log.warning("Could not close JDBC ResultSet" + e);
		}
	}

	/**
	 * Fecha a delcaracao de acesso a dados.
	 * 
	 * @param ps
	 * @throws SQLException
	 */
	protected void closeStatement(PreparedStatement ps) {
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		} catch (SQLException e) {
			log.warning("Could not close JDBC Statement"+ e);
		}
	}

	/**
	 * Fecha a conexão de acesso a dados.
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	protected void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			log.warning("Could not close JDBC Connection"+ e);
		}
	}

	/**
	 * Fecha conexão de acesso a dados e o conjunto de resultados e a declaracao
	 * associada a conexao.
	 * 
	 * @param conn
	 * @param rs
	 * @param ps
	 * @throws SQLException
	 */
	protected void closeConnection(Connection conn, ResultSet rs,
			PreparedStatement ps) {
		closeResultSet(rs);
		closeStatement(ps);
		closeConnection(conn);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * Recupera todas as entidade do tipo T.
	 * 
	 * @return entidade
	 */
	@SuppressWarnings("unchecked")
	public List<T> recuperarTodosOrdenados(String... ordenacoes) {
		List<T> retorno = null;
		Query q =
				entityManager.createQuery("SELECT c FROM " + getClassePersistente().getSimpleName() + " c "
						+ adicionaOrderByHql(ordenacoes));
		retorno = q.getResultList();
		return retorno;
	}
	/**
	 * Adiciona o orderBy no final da query a ser utilizada.
	 * 
	 * @param ordenacoes a serem utilizadas para a busca
	 * @return string com o orderBy
	 */
	protected static final String adicionaOrderByHql(String... ordenacoes) {
		StringBuilder builder = new StringBuilder();
		if (ordenacoes != null && ordenacoes.length > 0) {
			builder.append(" order by ");
			for (int i = 0; i < ordenacoes.length - 1; i++) {
				builder.append(ordenacoes[i].toString());
				builder.append(", ");
			}
			builder.append(ordenacoes[ordenacoes.length - 1]);
		}
		return builder.toString();
	}
	
}
