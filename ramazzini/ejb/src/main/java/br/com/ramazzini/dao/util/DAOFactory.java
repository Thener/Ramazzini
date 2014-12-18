package br.com.ramazzini.dao.util;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.ramazzini.model.util.AbstractEntidade;

/**
 * Classe que retorna o DAO e injeta o EntityManager neste de acordo com o nome
 * dos pacotes.
 * 
 */
@Stateless
@SuppressWarnings("unchecked")
@Named
public class DAOFactory implements DaoFactoryLocal {

    private static final String POS_FIXO_DAO = "Dao";
    private static final String PACOTE_ENTIDADE = "model.";
    private static final String PACOTE_DAO = "dao.";

    @Inject
    private Logger log;

    @Inject
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    public final AbstractDao<? extends AbstractEntidade> getDAO(final Class<? extends AbstractEntidade> classeEntidade) {
        try {
            Class<? extends AbstractDao> clazz = (Class<? extends AbstractDao>) Class.forName(getDAOName(classeEntidade));
            AbstractDao<? extends AbstractEntidade> result = createAbstractDao(clazz);
            if (entityManager != null) {
                result.setEntityManager(entityManager);
                return result;
            }
            //throw new Exception();// DAOFactoryException("exception.erro.tecnico");
        } catch (InstantiationException e) {
            //log.info(e.getMessage() e);
            //throw new DAOFactoryException("exception.erro.tecnico", e);
        } catch (IllegalAccessException e) {
            //log.error(e.getMessage(), e);
            //throw new DAOFactoryException("exception.erro.tecnico", e);
        } catch (ClassNotFoundException e) {
            //log.error(e.getMessage(), e);
            //throw new DAOFactoryException("exception.erro.tecnico", e);
        }
        
        return null;
    }

    /**
     * M�todo que busca o nome do DAO de acordo com o pacote do servi�o.
     * 
     * @param classeEntidade
     *            Classe de entidade
     * @return String com o DAO
     */
    private static String getDAOName(final Class<? extends AbstractEntidade> classeEntidade) {
        String resultado = classeEntidade.getName().replace(PACOTE_ENTIDADE, PACOTE_DAO);
        resultado = resultado.concat(POS_FIXO_DAO);
        return resultado;
    }

    AbstractDao<? extends AbstractEntidade> createAbstractDao(Class<? extends AbstractDao> clazz) 
    throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

}
