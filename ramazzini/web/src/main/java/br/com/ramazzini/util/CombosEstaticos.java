package br.com.ramazzini.util;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.UnidadeFederativa;

@Named("combosEstaticos") 
public class CombosEstaticos {	
	
	@Produces
	public UnidadeFederativa[] getUnidadesFederativas() {
		return UnidadeFederativa.values();
	}

}
