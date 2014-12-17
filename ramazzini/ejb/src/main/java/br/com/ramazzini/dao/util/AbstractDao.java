package br.com.ramazzini.dao.util;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
		
	@Inject
	private EntityManager entityManager;
	
	@Inject
    private Logger log;

	private Class<T> classePersistente = null;	
	
	public final void remover(T entidade) {
		DaoUtil.remover(entityManager, entidade);
	}

	@SuppressWarnings("unchecked")
	public final T salvar(T entidade, Usuario usuarioLogado) {
		return (T) DaoUtil.salvar(entityManager, entidade, usuarioLogado);
	}

	@SuppressWarnings("unchecked")
	public final T recuperarPorId(Long id) {
		return (T) DaoUtil.recuperarPorId(entityManager, getClassePersistente(), id);
	}
	
	@SuppressWarnings("unchecked")
	public final T recuperarPorIdForUpdate(Long id) {
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
	
}
