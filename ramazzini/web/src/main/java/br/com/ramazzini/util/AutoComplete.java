package br.com.ramazzini.util;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.service.EmpresaService;
import br.com.ramazzini.service.ProcedimentoService;

@Named
public class AutoComplete {

    @Inject
    private EmpresaService empresaService;
    
    @Inject
    ProcedimentoService procedimentoService;
    
    public List<Empresa> completeEmpresa(String query) {
    	if (!query.isEmpty())
        	return empresaService.recuperarPorNome(query);
        return null;
    }
    
    public List<Procedimento> completeProcedimento(String query) {            
        if (!query.isEmpty())
        	return procedimentoService.recuperarPorNome(query);
        return null;
    }	
}
