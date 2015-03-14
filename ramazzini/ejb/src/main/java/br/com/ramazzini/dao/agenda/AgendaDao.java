package br.com.ramazzini.dao.agenda;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.agenda.Agenda;
import br.com.ramazzini.model.profissional.Profissional;


public class AgendaDao extends AbstractDao<Agenda> {

	private static final String QUERY_LOAD = "Agenda.load";
	private static final String QUERY_RECUPERAR_POR_DATA_AGENDA = "Agenda.recuperarPorDataAgenda";
	private static final String QUERY_RECUPERAR_POR_DATA_AGENDA_SITUACAO = "Agenda.recuperarPorDataAgendaEsituacao";
	private static final String QUERY_RECUPERAR_PROFISSIONAIS_POR_DATA = "Agenda.recuperarProfissionaisPorData";
	
	/*
	 * Método responsável por carregar a agenda e todos os seus relacionamentos
	 */
	public Agenda load(Long id) {
		Query query = createNamedQuery(QUERY_LOAD);
		query.setParameter("id", id);
		try {
			return (Agenda) query.getSingleResult();
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
	
	@SuppressWarnings("unchecked")
	public List<Agenda> recuperarPorFiltros(Date data, String situacaoMarcacaoAgenda, Profissional profissional) {
		String consulta = "select a from Agenda a "
				+ "left join fetch a.funcionario f "
				+ "left join fetch f.funcao funcao "
				+ "left join fetch a.profissional p "
				+ "left join fetch a.procedimento proc "
				+ "left join fetch f.empresa e "
				+ "where 1=1 ";
		
		if (data != null) { consulta += " and a.dataAgenda = :data"; }
		if (!situacaoMarcacaoAgenda.isEmpty()) { consulta += " and a.situacaoMarcacaoAgenda = :situacaoMarcacaoAgenda"; }	
		if (profissional != null) { consulta += " and a.profissional = :profissional"; }		

		consulta += " order by a.horaChegada";
		
		Query query = createQuery(consulta);
		
		if (data != null) { query.setParameter("data", data); }
		if (!situacaoMarcacaoAgenda.isEmpty()) { query.setParameter("situacaoMarcacaoAgenda", situacaoMarcacaoAgenda); }
		if (profissional != null) { query.setParameter("profissional", profissional); }
		
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Profissional> recuperarProfissionaisPorData(Date data) {
		Query query = createNamedQuery(QUERY_RECUPERAR_PROFISSIONAIS_POR_DATA);
		query.setParameter("data", data);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
}