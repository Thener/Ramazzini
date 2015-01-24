package br.com.ramazzini.dao.parametro;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.parametro.Parametro;
import br.com.ramazzini.model.parametro.ParametroSistema;


public class ParametroDao extends AbstractDao<Parametro> {

	private static final String QUERY_RECUPERAR_POR_NOME = "Parametro.recuperarPorNome";
	
	public Parametro recuperarPorParametroSistema(ParametroSistema parametroSistema) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", parametroSistema.getValue());
		try {
			return (Parametro) query.getSingleResult();
		} catch (NoResultException nr) {
			Parametro p = new Parametro();
			p.setNome(parametroSistema.getValue());
			p.setValor(parametroSistema.getValorDefault());
			return p;
		}
	}	
		
}