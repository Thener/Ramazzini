package br.com.ramazzini.dao.util;

import javax.ejb.Local;

import br.com.ramazzini.model.util.AbstractEntidade;

/**
 * Interface local da classe DaoFactory.
 * 
 */
@Local
public interface DaoFactoryLocal {

	/**
	 * M�todo que busca o nome do DAO de acordo com o pacote do servi�o.
	 * 
	 * @param classeEntidade
	 *            Classe de entidade
	 * @return String com o DAO
	 */
	@SuppressWarnings("unchecked")
	AbstractDao getDAO(Class<? extends AbstractEntidade> classeEntidade);

}
