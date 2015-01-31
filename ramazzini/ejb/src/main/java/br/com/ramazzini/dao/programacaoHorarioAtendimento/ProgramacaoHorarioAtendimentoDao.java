package br.com.ramazzini.dao.programacaoHorarioAtendimento;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.horarioAtendimento.DiaSemana;
import br.com.ramazzini.model.horarioAtendimento.HorarioAtendimento;
import br.com.ramazzini.model.programacaoHorarioAtendimento.ProgramacaoHorarioAtendimento;


public class ProgramacaoHorarioAtendimentoDao extends AbstractDao<ProgramacaoHorarioAtendimento> {

	private static final String QUERY_RECUPERAR_POR_HORARIO_ATENDIMENTO = "ProgramacaoHorarioAtendimento.recuperarPorHorarioAtendimento";
	private static final String QUERY_RECUPERAR_POR_HORARIO_ATENDIMENTO_DIA_SEMANA = "ProgramacaoHorarioAtendimento.recuperarPorHorarioAtendimentoDiaSemana";
	
	@SuppressWarnings("unchecked")
	public List<ProgramacaoHorarioAtendimento> recuperarPorHorarioAtendimento(HorarioAtendimento horarioAtendimento) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_HORARIO_ATENDIMENTO);
		query.setParameter("horarioAtendimento", horarioAtendimento);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<ProgramacaoHorarioAtendimento> recuperarPorHorarioAtendimento(HorarioAtendimento horarioAtendimento, DiaSemana diaSemana) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_HORARIO_ATENDIMENTO_DIA_SEMANA);
		query.setParameter("horarioAtendimento", horarioAtendimento);
		query.setParameter("diaSemana", diaSemana.getValue());
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}