package br.com.ramazzini.dao.empresa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.empresa.Empresa;


public class EmpresaDao extends AbstractDao<Empresa> {

	private static final String QUERY_RECUPERAR_POR_NOME = "Empresa.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<Empresa> recuperarPorNome(String nome) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", "%"+nome+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}