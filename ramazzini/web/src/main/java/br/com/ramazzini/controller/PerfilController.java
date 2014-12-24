package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.service.PerfilService;
import br.com.ramazzini.service.TelaService;

@Named
public class PerfilController implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Inject
    private PerfilService perfilService;
    
    @Inject
    private TelaService telaService;    
    
    private List<Perfil> perfis;
    
    private DualListModel<String> telas;
	
	@PostConstruct
	public void init() {
		
		perfis = perfilService.recuperarTodos("nome");
		
		//--- Incluir PickList com módulos disponiveis para o perfil
		
		//--- Telas do Perfil:
		
		List<String> telasSource = new ArrayList<String>();
		List<String> telasTarget = new ArrayList<String>();
		
		List<Tela> todasAsTelas = telaService.recuperarTodos("nome");
		for (Tela t : todasAsTelas) {
			telasSource.add(t.getNome());
		}
		
		telas = new DualListModel<String>(telasSource, telasTarget);
		
	}
	
    public DualListModel<String> getTelas() {
        return telas;
    }	
    
    public void setTelas(DualListModel<String> telas) {
        this.telas = telas;
    }    

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}


}