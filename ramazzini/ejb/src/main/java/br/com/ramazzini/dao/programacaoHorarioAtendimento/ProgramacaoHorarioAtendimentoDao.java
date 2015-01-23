package br.com.ramazzini.dao.programacaoHorarioAtendimento;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.horarioAtendimento.HorarioAtendimento;
import br.com.ramazzini.model.programacaoHorarioAtendimento.ProgramacaoHorarioAtendimento;


public class ProgramacaoHorarioAtendimentoDao extends AbstractDao<ProgramacaoHorarioAtendimento> {

	private static final String QUERY_RECUPERAR_POR_HORARIO_ATENDIMENTO = "ProgramacaoHorarioAtendimento.recuperarPorHorarioAtendimento";
	
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
		
}