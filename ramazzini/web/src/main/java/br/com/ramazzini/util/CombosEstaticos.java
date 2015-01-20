package br.com.ramazzini.util;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.UnidadeFederativa;
import br.com.ramazzini.model.funcionario.Sexo;
import br.com.ramazzini.model.funcionario.SituacaoFuncionario;
import br.com.ramazzini.model.profissional.PapelProfissional;

@Named("combosEstaticos") 
public class CombosEstaticos {	
	
	@Produces
	public PapelProfissional[] getPapeisProfissional() {
		return PapelProfissional.values();
	}
	
	@Produces
	public Sexo[] getSexo() {
		return Sexo.values();
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