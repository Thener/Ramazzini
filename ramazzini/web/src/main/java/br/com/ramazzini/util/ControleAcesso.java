package br.com.ramazzini.util;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.ramazzini.model.acao.Acao;
import br.com.ramazzini.model.modulo.Modulo;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.perfilTela.PerfilTela;
import br.com.ramazzini.model.tela.Tela;

@Named
@RequestScoped
public class ControleAcesso implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	private HttpSession session;
    public ControleAcesso(){}
    
	public boolean acaoAutorizada(String modulo, String tela, String acao) {
		
		if (isAdministrador()) {
			return true;
		}
		
		boolean autorizada = false;
		
		sairLoop:
		for (Perfil p : getPerfis()) {
			
			if (p.getNome().equals("administrador")) {
				autorizada = true;
				break sairLoop;
			}
			
			for (PerfilTela pt : p.getPerfisTelas()) {
				if (pt.getTela().getModulo().getNome().equals(modulo) 
					&& pt.getTela().getNome().equals(tela)) {
					for (Acao a : pt.getAcoes()) {
						if (a.getNome().equals(acao)) {
							autorizada = true;
							break sairLoop;
						}
					}
				}
			}
		}
		
		return autorizada;
	}
    
	public boolean moduloAutorizado(String modulos) {
		
		if (isAdministrador()) {
			return true;
		}
		
		String mds[] = modulos.split("/");
		
		@SuppressWarnings("unchecked")
		List<Modulo> modulosUsuario = (List<Modulo>) session.getAttribute("usuarioModulos");
		
    	if (modulosUsuario == null || modulosUsuario.isEmpty() || modulosUsuario.get(0) == null) {
    		return false;
    	}
    	
		boolean autorizado = true;
		
		sairLoop:
		for (String modulo : mds) {
			for (Modulo m : modulosUsuario) {
				if (m.getNome().equals(modulo)) {
					autorizado = true;
					break sairLoop;
				}
			}
		}
		
		return autorizado;
	} 
	
	public boolean telaAutorizada(String modulo, String tela) {

		if (isAdministrador()) {
			return true;
		}

		boolean autorizado = false;
		
		sairLoop:
		for(Perfil p : getPerfis()) {
			
			for (PerfilTela perfilTtela : p.getPerfisTelas()) {
				
				if (perfilTtela.getTela().getModulo().getNome().equals(modulo)
						&& perfilTtela.getTela().getNome().equals(tela)) {

					autorizado = true;
					break sairLoop;
				}
			}
		}

		return autorizado;
	}
	
	/**
	 * Uma tela pode ser de acesso público, porém o usuário precisará estar autenticado. 
	 * Neste caso da tela ser de acesso público, a mesma não está associada a nenhum perfil, liberado o acesso
	 * a todos os usuários autenticados. 
	 * @param uri
	 * @return
	 */
	public boolean telaAcessoPublico(String uri) {
		
		@SuppressWarnings("unchecked")
		List<Tela> telasPublicas = (List<Tela>) getSession().getAttribute("telasPublicas");
		
		boolean acessoPublico = false;
		boolean telaCadastrada = false;
		String tela = getTela(uri);
		String modulo = getModulo(uri);
		
		sairLoop:
		for(Tela t : telasPublicas) {
				
			if (t.getModulo().getNome().equals(modulo)
					&& t.getNome().equals(tela)) {

				telaCadastrada = true;
				
				if (t.isPublico()) {
					acessoPublico = true;
				}
				break sairLoop;
			}
		}
		
		if (!telaCadastrada) {
			return false;
		}
		
		return acessoPublico;
	}	
	
	public boolean isAdministrador() {

		boolean administrador = false;
		
		for(Perfil p : getPerfis()) {

			if (p.getNome().equals("administrador")) {
				administrador = true;
				break;
			}
		}

		return administrador;
	}
	
	public boolean isAutorizado(String uri) {

		boolean autorizado = false;
		String mensagem = null;
		
		String tela = getTela(uri);
		String modulo = getModulo(uri);		
		
		sairLoop:
		for(Perfil p : getPerfis()) {

			if (!p.isAtivo()) {
				mensagem = "Perfil não está ativo!";
				continue;				
			}
			
			for (PerfilTela perfilTtela : p.getPerfisTelas()) {
				
				if (perfilTtela.getTela().getModulo().getNome().equals(modulo)
						&& perfilTtela.getTela().getNome().equals(tela)) {
					
					if (!perfilTtela.getTela().isAtiva()) {
						mensagem = "Tela está bloqueada no sistema. Por favor entre em contato com os Administradores do Sistema.";
						break sairLoop;
					}
					
					if (!perfilTtela.getTela().getModulo().isAtivo()) {
						mensagem = "Módulo " + perfilTtela.getTela().getModulo().getNome() + " está bloqueado no sistema. Por favor entre em contato com os Administradores do Sistema.";
						break sairLoop;
					}
					
					autorizado = true;
					mensagem = null;
					break sairLoop;
				}
			}
		}
		
		if (!autorizado && mensagem == null) {
			mensagem = "Desculpe, acesso não autorizado!";
		}
		
		session.setAttribute("mensagemControleAcesso", mensagem);

		return autorizado;
	}	

	@SuppressWarnings("unchecked")
	public List<Perfil> getPerfis() {
		return (List<Perfil>) getSession().getAttribute("usuarioPerfis");
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}	
	
	private static String getTela(String uri) {
		String partes[] = uri.split("/");
		return partes[ partes.length - 1 ];
	}
	
	private static String getModulo(String uri) {
		String partes[] = uri.split("/");
		return partes[ partes.length - 2 ];		
	}
	
	private HttpSession getSession() {
		if (session == null) {
			session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		}
		return session;
	}

	public String getUriRequisicao() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request.getRequestURI().substring(14);
	}	
}
