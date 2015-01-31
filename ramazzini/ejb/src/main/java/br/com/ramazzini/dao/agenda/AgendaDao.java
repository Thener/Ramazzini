package br.com.ramazzini.dao.agenda;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.agenda.Agenda;


public class AgendaDao extends AbstractDao<Agenda> {

	private static final String QUERY_RECUPERAR_POR_DATA_AGENDA = "Agenda.recuperarPorDataAgenda";
	private static final String QUERY_RECUPERAR_POR_DATA_AGENDA_SITUACAO = "Agenda.recuperarPorDataAgendaEsituacao";
	
	@SuppressWarnings("unchecked")
	public List<Agenda> recuperarPorDataAgenda(Date data) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_DATA_AGENDA);
		query.setParameter("data", data);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<Agenda> recuperarPorDataAgendaEsituacao(Date data, String situacaoMarcacaoAgenda) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_DATA_AGENDA_SITUACAO);
		query.setParameter("data", data);
		query.setParameter("situacaoMarcacaoAgenda", situacaoMarcacaoAgenda);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
}