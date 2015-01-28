package br.com.ramazzini.util;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

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
	
	@Produces
	public DiasMes[] getDiasMes() {
		return DiasMes.values();
	}	
	
	@Produces
	public DiaSemana[] getDiasSemana() {
		return DiaSemana.values();
	}
	
	@Produces
	public Mes[] getMeses() {
		return Mes.values();
	}	
	
	@Produces
	public PapelProfissional[] getPapeisProfissional() {
		return PapelProfissional.values();
	}
	
	@Produces
	public Sexo[] getSexo() {
		return Sexo.values();
	}
	
	@Produces
	public SimNao[] getSimNao() {
		return SimNao.values();
	}
	
	@Produces
	public NaoSim[] getNaoSim() {
		return NaoSim.values();
	}	
	
	@Produces
	public SituacaoFuncionario[] getSituacoesFuncionario() {
		return SituacaoFuncionario.values();
	}	
	
	@Produces
	public UnidadeFederativa[] getUnidadesFederativas() {
		return UnidadeFederativa.values();
	}

}