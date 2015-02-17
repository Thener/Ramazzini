package br.com.ramazzini.dao.guiaProcedimento;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.guia.Guia;
import br.com.ramazzini.model.guiaProcedimento.GuiaProcedimento;

public class GuiaProcedimentoDao extends AbstractDao<GuiaProcedimento> {

	private static final String QUERY_RECUPERAR_POR_GUIA = "GuiaProcedimento.recuperarPorGuia";
	
	@SuppressWarnings("unchecked")
	public List<GuiaProcedimento> recuperarPor(Guia guia) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_GUIA);
		query.setParameter("guia", guia);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}

}