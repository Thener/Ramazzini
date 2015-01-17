package br.com.ramazzini.dao.empresaServico;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.empresaServico.EmpresaServico;
import br.com.ramazzini.model.servico.Servico;


public class EmpresaServicoDao extends AbstractDao<EmpresaServico> {

	private static final String QUERY_RECUPERAR_POR_EMPRESA = "EmpresaServico.recuperarPorEmpresa";
	private static final String QUERY_RECUPERAR_POR_SERVICO = "EmpresaServico.recuperarPorServico";
	
	@SuppressWarnings("unchecked")
	public List<EmpresaServico> recuperarPorEmpresa(Empresa empresa) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_EMPRESA);
		query.setParameter("empresa", empresa);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<EmpresaServico> recuperarPorServico(Empresa empresa, Servico servico) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_SERVICO);
		query.setParameter("empresa", empresa);
		query.setParameter("servico", servico);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}