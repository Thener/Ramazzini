package br.com.ramazzini.dao.agenda;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.agenda.Agenda;
import br.com.ramazzini.model.profissional.Profissional;


public class AgendaDao extends AbstractDao<Agenda> {

	private static final String QUERY_RECUPERAR_PROFISSIONAIS_DISPONIVEIS_POR_DATA =
		"Agenda.recuperarProfissionaisDisponiveisPorData";
	private static final String QUERY_RECUPERAR_POR_DATA_AGENDA = "Agenda.recuperarPorDataAgenda";	
	
	@SuppressWarnings("unchecked")
	public List<Profissional> recuperarProfissionaisDisponiveisPorData(Date data) {
		Query query = createNamedQuery(QUERY_RECUPERAR_PROFISSIONAIS_DISPONIVEIS_POR_DATA);
		query.setParameter("data", data);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
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
}