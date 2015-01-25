package br.com.ramazzini.service.util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.dao.util.DaoFactoryLocal;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.model.util.AbstractEntidade;
import br.com.ramazzini.util.ReflectionUtil;

/**
 * Classe genérica de servico que possui os métodos de serviços reutilizáveis.
 * 
 * @param <T>
 */
public abstract class AbstractServiceImpl<T extends AbstractEntidade>
		implements AbstractServiceInterface<T> {

	private final Class<? extends AbstractEntidade> classePersistente;
	@Inject
	private DaoFactoryLocal daoFactory;
	
    @Inject
    private HttpSession session;	

	/**
	 * Construtor da classe abstrata de serviços.
	 */
	@SuppressWarnings("unchecked")
	public AbstractServiceImpl() {
		classePersistente = (Class<T>) ReflectionUtil.getParameterizedType(
				getClass()).getActualTypeArguments()[0];
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected final AbstractDao<T> getDao() {
		return daoFactory.getDAO(classePersistente);
	}

	/**
	 * 
	 * @return
	 */
	public T recuperarPorId(Long id) {
		return getDao().recuperarPorId(id);
	}

	/**
	 * 
	 * @return
	 */
	public T recuperarPorIdForUpdate(Long id) {
		return getDao().recuperarPorIdForUpdate(id);
	}
	
	/**
	 * 
	 * @return
	 */
	public void remover(T entidade, Long id) {
		getDao().remover(entidade, id);
	}

	/**
	 * Metodo que deve ser sobrescrito para realizar validacoes 'cross-fields'
	 * e/ou de regras de negocio nas entidades. Para a validacao falhar, basta
	 * lancar uma exception. O proprio programador deve tratar a exception em
	 * seu 'Manager Bean' fazendo o tratamento, podendo utilizar o FacesMessages
	 * e redirecionar para propria pagina.
	 * 
	 * @param entidade
	 */
	protected void validar(T entidade) {
		// Realiza validacoes complexas da entidade
	}

	public T salvar(T entidade) {
		return getDao().salvar(entidade, getUsuarioLogado());
	}
	
	public void clear() {
		getDao().clear();
	}	
	
	public void refresh(AbstractEntidade entidade) {
		getDao().refresh(entidade);
	}	
	
	public List<T> recuperarTodos(String... orderBy) {
		return getDao().recuperarTodosOrdenados(orderBy);
	}
	
	public List<T> recuperarTodos() {
		return getDao().recuperarTodos();
	}	

	
	public void initTransaction() {
		// TODO Auto-generated method stub
		
	}

	
	public void endTransaction() {
		// TODO Auto-generated method stub
		
	}

	
	public void evict(AbstractEntidade entidade) {
		// TODO Auto-generated method stub
		
	}
	public List<T> salvarLista(List<T> listaEntidade) {
		List<T> listaSalva = new ArrayList<T>();
		for (T entidade : listaEntidade) {
			salvar(entidade);
			listaSalva.add(entidade);
		}
		return listaSalva;
	}
	
	public Usuario getUsuarioLogado() {
		return (Usuario) session.getAttribute("usuario");
	}
}
