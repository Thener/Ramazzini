package br.com.ramazzini.util;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.lotacao.Lotacao;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.LotacaoService;
import br.com.ramazzini.service.ProfissionalService;

@Named("combosDinamicos")
public class CombosDinamicos {

    @Inject
    LotacaoService lotacaoService; 
    
    @Inject
    ProfissionalService profissionalService;
    
	public List<Lotacao> getLotacoes(Empresa empresa) {
		return lotacaoService.recuperarPorEmpresa(empresa);
	}	
	
	public List<Profissional> getProfissionais() {
		return profissionalService.recuperarTodos("nome");
	}	
}
