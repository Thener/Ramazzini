package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.ramazzini.model.acao.Acao;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.perfilTela.PerfilTela;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Inject
    private HttpSession session;	
	
	public boolean acaoAutorizada(String modulo, String tela, String acao) {
		
		@SuppressWarnings("unchecked")
		List<Perfil> perfis = (List<Perfil>) session.getAttribute("usuarioPerfis");
		
		boolean autorizada = false;
		
		sairLoop:
		for (Perfil p : perfis) {
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
    
    

}