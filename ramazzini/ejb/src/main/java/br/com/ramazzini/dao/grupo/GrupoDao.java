package br.com.ramazzini.dao.grupo;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.grupo.Grupo;


public class GrupoDao extends AbstractDao<Grupo> {

//	private static final String QUERY_RECUPERAR_POR_NOME = "Grupo.recuperarPorNome";
	private static final String QUERY_RECUPERAR_TODAS_EMPRESAS = "Grupo.recuperarTodasEmpresas";
	
//	@SuppressWarnings("unchecked")
//	public List<Grupo> recuperarPorNome(String nome) {
//		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
//		query.setParameter("nome", "%"+nome+"%");
//		try {
//			return query.getResultList();
//		} catch (NoResultException nr) {
//			return null;
//		}
//	}	
	
	@SuppressWarnings("unchecked")
	public List<Empresa> recuperarTodasEmpresas(Grupo grupo) {
		Query query = createNamedQuery(QUERY_RECUPERAR_TODAS_EMPRESAS);
		query.setParameter("grupo", grupo);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}