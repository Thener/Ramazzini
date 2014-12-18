package br.com.ramazzini.service.util;

import javax.inject.Inject;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.dao.util.DaoFactoryLocal;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.model.util.AbstractEntidade;
import br.com.ramazzini.util.ReflectionUtil;

/**
 * Classe gen�rica de servico que possui os m�todos de servi�o reutiliz�veis por
 * todas as classes de servi�o existentes.
 * 
 * @param <T>
 *            T extends AbstractEntidade
 * @author samuelmd
 * 
 */
public abstract class AbstractServiceImpl<T extends AbstractEntidade>
		implements AbstractServiceInterface<T> {

	private final Class<? extends AbstractEntidade> classePersistente;

	@Inject
	private DaoFactoryLocal daoFactory;

	/**
	 * Construtor da classe abstrata de servi�os.
	 */
	@SuppressWarnings("unchecked")
	public AbstractServiceImpl() {
		classePersistente = (Class<T>) ReflectionUtil.getParameterizedType(
				getClass()).getActualTypeArguments()[0];
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	protected final AbstractDao<T> getDao() {
		return daoFactory.getDAO(classePersistente);
	}

	/**
	 * {@inheritDoc}
	 */
	public T recuperarPorId(Long id) {
		return getDao().recuperarPorId(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public T recuperarPorIdForUpdate(Long id) {
		return getDao().recuperarPorIdForUpdate(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void remover(T entidade) {
		getDao().remover(entidade);
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

	/**
	 * {@inheritDoc}
	 */
	public T salvar(T entidade, Usuario usuarioLogado) {
		validar(entidade);

		return getDao().salvar(entidade, usuarioLogado);
	}


	@Override
	public void clear() {
		getDao().clear();
	}

	
	@Override
	public void refresh(AbstractEntidade entidade) {
		getDao().refresh(entidade);
	}
	


}