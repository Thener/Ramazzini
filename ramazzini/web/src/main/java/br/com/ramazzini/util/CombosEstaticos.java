package br.com.ramazzini.util;

import javax.inject.Named;

import br.com.ramazzini.model.agenda.SituacaoMarcacaoAgenda;
import br.com.ramazzini.model.agenda.StatusAtualizacaoAgenda;
import br.com.ramazzini.model.agenda.StatusNotificacaoAgenda;
import br.com.ramazzini.model.agenda.TempoAtualizacaoAgenda;
import br.com.ramazzini.model.avaliacaoClinica.SituacaoAvaliacaoClinica;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.ResultadoProcedimento;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.TipoAlteracaoProcedimento;
import br.com.ramazzini.model.empresa.SituacaoEmpresa;
import br.com.ramazzini.model.empresa.UnidadeFederativa;
import br.com.ramazzini.model.enquadramentoDeficiencia.EnquadramentoDeficienciaEnum;
import br.com.ramazzini.model.feriado.DiasMes;
import br.com.ramazzini.model.feriado.Mes;
import br.com.ramazzini.model.funcionario.Sexo;
import br.com.ramazzini.model.funcionario.SituacaoFuncionario;
import br.com.ramazzini.model.horarioAtendimento.DiaSemana;
import br.com.ramazzini.model.limitacoesDeficienciaMental.LimitacoesDeficienciaMentalEnum;
import br.com.ramazzini.model.origemDeficiencia.OrigemDeficienciaEnum;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.model.procedimento.TipoProcedimento;
import br.com.ramazzini.model.profissional.PapelProfissional;
import br.com.ramazzini.model.util.NaoSim;
import br.com.ramazzini.model.util.SimNao;

@Named("combosEstaticos") 
public class CombosEstaticos {	
	
	public DiasMes[] getDiasMes() {
		return DiasMes.values();
	}	
	
	public DiaSemana[] getDiasSemana() {
		return DiaSemana.values();
	}
	
	public Mes[] getMeses() {
		return Mes.values();
	}	
	
	public PapelProfissional[] getPapeisProfissional() {
		return PapelProfissional.values();
	}
	
	public ResultadoProcedimento[] getResultadosProcedimento() {
		return ResultadoProcedimento.values();
	}	
	
	public Sexo[] getSexo() {
		return Sexo.values();
	}
	
	public SimNao[] getSimNao() {
		return SimNao.values();
	}
	
	public NaoSim[] getNaoSim() {
		return NaoSim.values();
	}	

	public SituacaoAvaliacaoClinica[] getSituacoesAvaliacaoClinica() {
		return SituacaoAvaliacaoClinica.values();
	}
	
	public SituacaoFuncionario[] getSituacoesFuncionario() {
		return SituacaoFuncionario.values();
	}	
	
	public OrigemDeficienciaEnum[] getOrigemDeficiencia() {
		return OrigemDeficienciaEnum.values();
	}	
	
	public EnquadramentoDeficienciaEnum[] getEnquadramentoDeficiencia() {
		return EnquadramentoDeficienciaEnum.values();
	}	
	
	public LimitacoesDeficienciaMentalEnum[] getLimitacoesDeficienciaMental() {
		return LimitacoesDeficienciaMentalEnum.values();
	}
	
	public SituacaoEmpresa[] getSituacoesEmpresa() {
		return SituacaoEmpresa.values();
	}	
	
	public SituacaoMarcacaoAgenda[] getSituacoesMarcacaoAgenda() {
		return SituacaoMarcacaoAgenda.values();
	}	
	
	public StatusAtualizacaoAgenda[] getStatusAtualizacaoAgenda() {
		return StatusAtualizacaoAgenda.values();
	}
	
	public StatusNotificacaoAgenda[] getStatusNotificacaoAgenda() {
		return StatusNotificacaoAgenda.values();
	}
	
	public TipoAlteracaoProcedimento[] getTiposAlteracaoProcedimento() {
		return TipoAlteracaoProcedimento.values();
	}
	
	public TipoExameClinico[] getTiposExameClinico() {
		return TipoExameClinico.values();
	}
	
	public TipoProcedimento[] getTiposProcedimento() {
		return TipoProcedimento.values();
	}
	
	public TempoAtualizacaoAgenda[] getTemposAtualizacaoAgenda() {
		return TempoAtualizacaoAgenda.values();
	}		
	
	public UnidadeFederativa[] getUnidadesFederativas() {
		return UnidadeFederativa.values();
	}

}