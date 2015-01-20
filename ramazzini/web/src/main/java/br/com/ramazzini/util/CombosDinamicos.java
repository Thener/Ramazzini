package br.com.ramazzini.util;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.ProfissionalService;

@Named("combosDinamicos")
public class CombosDinamicos {

    @Inject
    ProfissionalService profissionalService;
    
	public List<Profissional> getProfissionais() {
		return profissionalService.recuperarTodos("nome");
	}	
}
