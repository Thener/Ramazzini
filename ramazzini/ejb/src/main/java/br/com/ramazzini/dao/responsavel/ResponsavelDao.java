package br.com.ramazzini.dao.responsavel;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.responsavel.Responsavel;


public class ResponsavelDao extends AbstractDao<Responsavel> {

	private static final String QUERY_RECUPERAR_POR_EMPRESA = "Responsavel.recuperarPorEmpresa";
	private static final String QUERY_RECUPERAR_POR_NOME_PROFISSIONAL = "Responsavel.recuperarPorNomeProfissional";
	
	@SuppressWarnings("unchecked")
	public List<Responsavel> recuperarPorEmpresa(Empresa empresa) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_EMPRESA);
		query.setParameter("empresa", empresa);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Responsavel> recuperarPorNomeProfissional(Empresa empresa, String nomeProfissional) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME_PROFISSIONAL);
		query.setParameter("empresa", empresa);
		query.setParameter("nomeProfissional", "%"+nomeProfissional+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}