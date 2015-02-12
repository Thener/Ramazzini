package br.com.ramazzini.dao.enquadramentoDeficiencia;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.enquadramentoDeficiencia.EnquadramentoDeficiencia;
import br.com.ramazzini.model.enquadramentoDeficiencia.EnquadramentoDeficienciaEnum;


public class EnquadramentoDeficienciaDao extends AbstractDao<EnquadramentoDeficiencia> {
	
	private static final String QUERY_RECUPERAR_POR_ENQUADRAMENTO = "EnquadramentoDeficiencia.recuperarPorTipoEnquadramento";
	
	public EnquadramentoDeficiencia recuperarPorTipoEnquadramento(EnquadramentoDeficienciaEnum enquadramento) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_ENQUADRAMENTO);
		query.setParameter("tipo_enquadramento", enquadramento.getValue());
		try {
			return (EnquadramentoDeficiencia) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		}
	}
}