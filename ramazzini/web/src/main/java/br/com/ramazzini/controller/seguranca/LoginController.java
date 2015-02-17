package br.com.ramazzini.controller.seguranca;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.service.seguranca.UsuarioService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class LoginController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private UsuarioService usuarioService;
	@Inject private HttpSession session;
	@Inject private FacesContext facesContext;	

	private Usuario usuario = new Usuario();
	
	private boolean lembrarEmail;
	
	private static final String COOKIE_LEMBRAR_USUARIO = "lembrarUsuario";

	@PostConstruct
	public void init() {
		beginConversation();
		String email = recuperarCookie(COOKIE_LEMBRAR_USUARIO);
		if (email != "") {
			usuario.setLogin(email);
			lembrarEmail = true;
		} else {
			lembrarEmail = false;
		}
	}

	public String login() {

		if (usuarioService.autenticar(usuario.getLogin(), usuario.getSenha())) {
			String login = lembrarEmail ? usuario.getLogin() : ""; 
			gravarCookie(COOKIE_LEMBRAR_USUARIO, login, 432000);
			return "/pages/home/home.jsf";
		} else {
			UtilMensagens.mensagemErro("Acesso Negado! Favor verificar os dados informados.");
		}

		return ""; // Sem argumentos para voltar para a página de login
	}

	public String logout() {

		session.invalidate();
		return "/index.jsf?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLembrarEmail() {
		return lembrarEmail;
	}

	public void setLembrarEmail(boolean lembrarEmail) {
		this.lembrarEmail = lembrarEmail;
	}

	public void gravarCookie(String nome, String valor, int tempo) {

		if (facesContext != null) {
			Cookie ck = new Cookie(nome, valor);
			ck.setMaxAge(tempo);
			((HttpServletResponse) facesContext.getExternalContext().getResponse())
					.addCookie(ck);
		}
	}
	
	public String recuperarCookie(String nome) {
		
        if (facesContext != null){  
             Map<String, Object> cookies = facesContext.getExternalContext().getRequestCookieMap();  
             Cookie cookie = (Cookie) cookies.get(nome);  
             if (cookie != null) {
            	 return cookie.getValue();
             }  
        } 		

        return null;
	}

}
