package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.modulo.Modulo;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.service.ModuloService;
import br.com.ramazzini.service.PerfilService;
import br.com.ramazzini.service.TelaService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class PerfilController implements Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject Conversation conversation;

	@Inject
	private PerfilService perfilService;

	@Inject
	private ModuloService moduloService;

	@Inject
	private TelaService telaService;

	private List<Perfil> perfis;

	private List<Modulo> modulos;

	private List<Tela> telas;
	
	private List<Tela> telasDoPerfil;

	private Perfil perfilSelecionado;

	private Modulo moduloSelecionado;

	private Tela telaSelecionada;

	@PostConstruct
	public void init() {

		perfis = perfilService.recuperarTodos("nome");
		setModulos(moduloService.recuperarTodos("nome"));

		if (conversation.isTransient()) {
			conversation.begin();
		}
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

	public String alterarPerfil(Perfil perfil) {
		setPerfilSelecionado(perfil);
		setModulos(moduloService.recuperarTodos("nome"));
		return "/pages/perfil/alterarPerfil.jsf";
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public Modulo getModuloSelecionado() {
		return moduloSelecionado;
	}

	public void setModuloSelecionado(Modulo moduloSelecionado) {
		this.moduloSelecionado = moduloSelecionado;
	}

	public List<Tela> getTelas() {
		return telas;
	}

	public void setTelas(List<Tela> telas) {
		this.telas = telas;
	}

	public Tela getTelaSelecionada() {
		return telaSelecionada;
	}

	public void setTelaSelecionada(Tela telaSelecionada) {
		this.telaSelecionada = telaSelecionada;
	}

	public void perfilChange() {
		setTelas(telaService.recuperarPorModulo(getModuloSelecionado(), false, "nome"));
	}
	
    public void autorizarTela() {
        
    	if (perfilService.incluirTelaVerificandoExistencia(perfilSelecionado, telaSelecionada) != null) {
    		UtilMensagens.mensagemInformacao("Tela incluída com sucesso!");
    	} else {
    		UtilMensagens.mensagemErro("Tela já autorizada");
    	}

    }

	public List<Tela> getTelasDoPerfil() {
		return telaService.recuperarPorPerfil(perfilSelecionado);
	}    
}