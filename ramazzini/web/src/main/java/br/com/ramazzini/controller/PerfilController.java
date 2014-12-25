package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.service.PerfilService;

@Named
public class PerfilController implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Inject
    private PerfilService perfilService;

    private List<Perfil> perfis;
    
    private Perfil perfilSelecionado;
    
	@PostConstruct
	public void init() {
		
		perfis = perfilService.recuperarTodos("nome");

	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Perfil getPerfilSelecionado() {
		return perfilSelecionado;
	}

	public void setPerfilSelecionado(Perfil perfilSelecionado) {
		this.perfilSelecionado = perfilSelecionado;
	}

	public String visualizarPerfil(Perfil perfil) {
		
		return "/pages/home/home.jsf";
	}
	
	public String alterarPerfil(Perfil perfil) {
		
		return "/pages/home/home.jsf";
	}	
}