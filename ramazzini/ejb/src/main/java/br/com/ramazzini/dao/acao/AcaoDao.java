package br.com.ramazzini.dao.acao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.acao.Acao;
import br.com.ramazzini.model.tela.Tela;


public class AcaoDao extends AbstractDao<Acao> {

	private static final String QUERY_RECUPERAR_POR_PERFIL_TELA = "Acao.recuperarPorTela";
	
	@SuppressWarnings("unchecked")
	public List<Acao> recuperarPorTela(Tela tela) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_PERFIL_TELA);
		query.setParameter("tela", tela);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}