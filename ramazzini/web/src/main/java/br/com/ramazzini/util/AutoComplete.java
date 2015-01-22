package br.com.ramazzini.util;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.riscoOcupacional.RiscoOcupacional;
import br.com.ramazzini.service.entidade.EmpresaService;
import br.com.ramazzini.service.entidade.ProcedimentoService;
import br.com.ramazzini.service.entidade.RiscoOcupacionalService;

@Named
public class AutoComplete {

    @Inject
    private EmpresaService empresaService;
    
    @Inject
    ProcedimentoService procedimentoService;
    
    @Inject
    RiscoOcupacionalService riscoOcupacionalService;    
    
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
    
    public List<RiscoOcupacional> completeRiscoOcupacional(String query) {            
        if (!query.isEmpty())
        	return riscoOcupacionalService.recuperarPorNome(query);
        return null;
    }    
}
