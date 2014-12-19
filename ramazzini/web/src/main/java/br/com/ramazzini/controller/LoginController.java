package br.com.ramazzini.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.service.UsuarioService;

@Named
@ConversationScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Conversation conversation;
	
    @Inject
    private UsuarioService usuarioService;
	
    @Inject
    private HttpSession session;
	
	private Usuario usuario = new Usuario();
	
	@PostConstruct
	public void init() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	public String login() {

		Usuario usuarioBd = usuarioService.recuperarPorLogin(usuario.getLogin());
		
		if (usuarioBd != null && usuarioBd.getSenha().equals(usuario.getSenha())) {
			session.setAttribute("usuario", usuarioBd);
			return "/pages/home/home.xhtml";
		}
		
		return ""; // Sem argumentos para voltar para a página de login
	}
	
	public String logout() {
		
		session.invalidate();
		return "/pages/login/login.xhtml";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
