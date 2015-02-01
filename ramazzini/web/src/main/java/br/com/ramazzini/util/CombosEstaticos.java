package br.com.ramazzini.util;

import javax.inject.Named;

import br.com.ramazzini.model.agenda.SituacaoMarcacaoAgenda;
import br.com.ramazzini.model.agenda.TempoAtualizacaoAgenda;
import br.com.ramazzini.model.empresa.UnidadeFederativa;
import br.com.ramazzini.model.feriado.DiasMes;
import br.com.ramazzini.model.feriado.Mes;
import br.com.ramazzini.model.funcionario.Sexo;
import br.com.ramazzini.model.funcionario.SituacaoFuncionario;
import br.com.ramazzini.model.horarioAtendimento.DiaSemana;
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
	
	public Sexo[] getSexo() {
		return Sexo.values();
	}
	
	public SimNao[] getSimNao() {
		return SimNao.values();
	}
	
	public NaoSim[] getNaoSim() {
		return NaoSim.values();
	}	
	
	public SituacaoFuncionario[] getSituacoesFuncionario() {
		return SituacaoFuncionario.values();
	}	
	
	public SituacaoMarcacaoAgenda[] getSituacoesMarcacaoAgenda() {
		return SituacaoMarcacaoAgenda.values();
	}	
	
	public TempoAtualizacaoAgenda[] getTemposAtualizacaoAgenda() {
		return TempoAtualizacaoAgenda.values();
	}		
	
	public UnidadeFederativa[] getUnidadesFederativas() {
		return UnidadeFederativa.values();
	}

}