package br.com.ramazzini.util;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.ramazzini.model.acao.Acao;
import br.com.ramazzini.model.modulo.Modulo;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.perfilTela.PerfilTela;

public class ControleAcesso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private HttpSession session;
    
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
	
	private boolean isAdministrador() {

		boolean administrador = false;
		
		for(Perfil p : getPerfis()) {

			if (p.getNome().equals("administrador")) {
				administrador = true;
				break;
			}
		}

		return administrador;
	}

	@SuppressWarnings("unchecked")
	public List<Perfil> getPerfis() {
		 return (List<Perfil>) session.getAttribute("usuarioPerfis");
	}	
}
