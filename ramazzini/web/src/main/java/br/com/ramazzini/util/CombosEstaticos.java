package br.com.ramazzini.util;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.UnidadeFederativa;
import br.com.ramazzini.model.profissional.PapelProfissional;

@Named("combosEstaticos") 
public class CombosEstaticos {	
	
	@Produces
	public UnidadeFederativa[] getUnidadesFederativas() {
		return UnidadeFederativa.values();
	}
	
	@Produces
	public PapelProfissional[] getPapeisProfissional() {
		return PapelProfissional.values();
	}	

}
