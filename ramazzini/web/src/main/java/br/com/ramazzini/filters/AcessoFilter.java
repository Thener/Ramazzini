package br.com.ramazzini.filters;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.perfilTela.PerfilTela;
import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.service.TelaService;

@WebFilter("/AcessoFilter")
public class AcessoFilter implements Filter {

    @Inject
    private static TelaService telaService;
    
    @Inject
    private static Logger log;    
    
    public AcessoFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		
		if (uri.endsWith("/login.jsf")) {
			chain.doFilter(req, res);
			return;
		}
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsf");
		} else {
			if (isAdministrador(session) || telaAcessoPublico(uri) || isAutorizado(uri, session)) {
				chain.doFilter(req, res);
			} else {
				response.sendRedirect(request.getContextPath() + "/pages/home/acessoNaoAutorizado.jsf");	
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}
	
	private static String getTela(String uri) {
		String partes[] = uri.split("/");
		return partes[ partes.length - 1 ];
	}
	
	private static String getModulo(String uri) {
		String partes[] = uri.split("/");
		return partes[ partes.length - 2 ];		
	}
	
	private static boolean isAutorizado(String uri, HttpSession session) {

		@SuppressWarnings("unchecked")
		List<Perfil> usuarioPerfis = (List<Perfil>) session.getAttribute("usuarioPerfis");
		
		boolean autorizado = false;
		String mensagem = null;
		
		sairLoop:
		for(Perfil p : usuarioPerfis) {

			if (!p.isAtivo()) {
				mensagem = "Perfil não está ativo!";
				continue;				
			}
			
			for (PerfilTela perfilTtela : p.getPerfisTelas()) {
				
				if (perfilTtela.getTela().getModulo().getNome().equals(getModulo(uri))
						&& perfilTtela.getTela().getNome().equals(getTela(uri))) {
					
					if (!perfilTtela.getTela().isAtivo()) {
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
	
	private static boolean isAdministrador(HttpSession session) {

		@SuppressWarnings("unchecked")
		List<Perfil> usuarioPerfis = (List<Perfil>) session.getAttribute("usuarioPerfis");
		
		boolean administrador = false;
		
		for(Perfil p : usuarioPerfis) {

			if (p.getNome().equals("administrador")) {
				administrador = true;
				break;
			}
		}

		return administrador;
	}	
	
	/**
	 * Uma tela pode ser de acesso público, porém o usuário precisará estar autenticado. 
	 * Neste caso da tela ser de acesso público, a mesma não está associada a nenhum perfil, liberado o acesso
	 * a todos os usuários autenticados. 
	 * @param uri
	 * @return
	 */
	private static boolean telaAcessoPublico(String uri) {
		
		Tela tela = telaService.recuperarPorModuloTela(getModulo(uri), getTela(uri));
		
		if (tela == null) {
			log.info("Erro: Módulo [" + getModulo(uri) + "] e tela [" + getTela(uri) + "] não estão cadastrados no sistema.");
			return false;
		}
		
		return tela.isPublico() ? true : false;
	}

}