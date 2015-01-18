package br.com.ramazzini.util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.service.EmpresaService;

@Named
public class AutoComplete {

    @Inject
    private EmpresaService empresaService;
    
    public List<Empresa> completeEmpresa(String query) {
        List<Empresa> todasEmpresas = empresaService.recuperarTodos();
        List<Empresa> empresasFiltradas = new ArrayList<Empresa>();
         
        for (int i = 0; i < todasEmpresas.size(); i++) {
        	Empresa emp = todasEmpresas.get(i);
            if(emp.getNome().toLowerCase().contains(query)) {
            	empresasFiltradas.add(emp);
            }
        }         
        return empresasFiltradas;
    }
}
