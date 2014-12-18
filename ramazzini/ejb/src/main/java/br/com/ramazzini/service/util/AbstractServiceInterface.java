package br.com.ramazzini.service.util;

import java.util.List;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.model.util.AbstractEntidade;

/**
 * Interface gen�rica dos servi�os que possuem os m�todos dispon�veis para
 * todos os servi�os que herdam de GenericServiceImpl.
 * 
 * @author samuelmd
 * 
 * @param <T>
 */
public interface AbstractServiceInterface<T extends AbstractEntidade> {

	/**
	 * M�todo respons�vel por recuperar um objeto do tipo entidade.
	 * 
	 * @param id
	 *            String com id
	 * @return entidade
	 */
	T recuperarPorId(Long id);

	/**
	 * M�todo respons�vel por recuperar um objeto do tipo entidade com lock
	 * no registro do banco.
	 * 
	 * @param id
	 *            String com id
	 * @return entidade
	 */
	T recuperarPorIdForUpdate(Long id);

	/**
	 * M�todo respons�vel por recuperar todos os objetos do tipo entidade.
	 * 
	 * @return lista de entidades
	 */
	List<T> recuperarTodos();

	/**
	 * M�todo respons�vel por recuperar todos os objetos do tipo entidade
	 * ordenados pelos atributos passados por par�metro.
	 * 
	 * @return lista de entidades
	 */
	List<T> recuperarTodos(String... orderBy);

	/**
	 * M�todo respons�vel por remover um objeto do tipo entidade.
	 * 
	 * @param entidade
	 *            entidade
	 */
	void remover(T entidade);

	/**
	 * M�todo respons�vel por armazenar um objeto do tipo entidade.
	 * 
	 * @param entidade
	 *            entidade
	 * @param usuarioLogado
	 *            usuario logado no sistema
	 */
	T salvar(T entidade, Usuario usuarioLogado);

	/**
	 * Inicializa uma transa��o pesada no banco de dados, simplesmente n�o
	 * executando o <code>flush()</code> no m�todo
	 * {@link AbstractDao#salvar(AbstractEntidade, Usuario)}.
	 */
	void initTransaction();

	/**
	 * Executa o <code>flush()</code> para liberar o cache do Hibernate e
	 * encerra o bloco de transa��o.
	 */
	void endTransaction();

	/**
	 * Executa o <code>clear()</code> para liberar o cache 1o nivel do Hibernate.
	 */
	void clear();

	/**
	 * Desconecta uma entidade da sess�o do Hibernate.
	 * 
	 * @param entidade
	 */
	void evict(AbstractEntidade entidade);

	/**
	 * Atualiza a entidade com os valores atuais do banco de dados.
	 * 
	 * @param entidade
	 */	
	void refresh(AbstractEntidade entidade);
	
	/**
	 * 
	 * @param listaEntidades
	 * @param usuarioLogado
	 * @return
	 */
	List<T> salvarLista(List<T> listaEntidades, Usuario usuarioLogado);
	
}
