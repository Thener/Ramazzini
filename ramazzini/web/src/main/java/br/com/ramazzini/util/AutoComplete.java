package br.com.ramazzini.util;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.riscoOcupacional.RiscoOcupacional;
import br.com.ramazzini.service.entidade.CredenciadoService;
import br.com.ramazzini.service.entidade.EmpresaService;
import br.com.ramazzini.service.entidade.FuncionarioService;
import br.com.ramazzini.service.entidade.ProcedimentoService;
import br.com.ramazzini.service.entidade.RiscoOcupacionalService;

@Named
public class AutoComplete {

    @Inject EmpresaService empresaService;
    @Inject ProcedimentoService procedimentoService;
    @Inject CredenciadoService credenciadoService;
    @Inject FuncionarioService funcionarioService;    
    @Inject RiscoOcupacionalService riscoOcupacionalService;    
    
    public List<Credenciado> completeCredenciado(String query) {            
        if (!query.isEmpty())
        	return credenciadoService.recuperarPorNome(query);
        return null;
    }
    
    public List<Empresa> completeEmpresa(String query) {
    	if (!query.isEmpty())
        	return empresaService.recuperarPorNome(query);
        return null;
    }
    
    public List<Funcionario> completeFuncionario(String nomeFuncionario) {
    	if (!nomeFuncionario.isEmpty()) {
    		FacesContext context = FacesContext.getCurrentInstance();
    	    Empresa empresa = (Empresa) UIComponent.getCurrentComponent(context).getAttributes().get("empresaSelecionada");
        	return funcionarioService.recuperarPorNome(empresa, nomeFuncionario);
    	}
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
