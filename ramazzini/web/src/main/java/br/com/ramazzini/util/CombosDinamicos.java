package br.com.ramazzini.util;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.cbo.Cbo;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.lotacao.Lotacao;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.model.setor.Setor;
import br.com.ramazzini.service.CboService;
import br.com.ramazzini.service.LotacaoService;
import br.com.ramazzini.service.ProfissionalService;
import br.com.ramazzini.service.SetorService;

@Named("combosDinamicos")
public class CombosDinamicos {

    @Inject
    CboService cboService; 
    
    @Inject
    LotacaoService lotacaoService; 
    
    @Inject
    ProfissionalService profissionalService;
    
    @Inject
    SetorService setorService;    
    
	public List<Cbo> getCbos() {
		return cboService.recuperarTodos("numero");
	}
	
	public List<Lotacao> getLotacoes(Empresa empresa) {
		return lotacaoService.recuperarPorEmpresa(empresa);
	}	
	
	public List<Profissional> getProfissionais() {
		return profissionalService.recuperarTodos("nome");
	}
	
	public List<Setor> getSetores(Empresa empresa) {
		return setorService.recuperarPorEmpresa(empresa);
	}		
}
