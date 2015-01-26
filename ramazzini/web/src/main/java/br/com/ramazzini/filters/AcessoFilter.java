package br.com.ramazzini.filters;

import java.io.IOException;

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

import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.util.Contexto;
import br.com.ramazzini.util.ControleAcesso;

@WebFilter("/AcessoFilter")
public class AcessoFilter implements Filter {
    
    public AcessoFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		Contexto context = Contexto.newInstance((HttpServletRequest) request);
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		ControleAcesso controleAcesso = new ControleAcesso();
		
		controleAcesso.setSession(session);
		
		String uri = request.getRequestURI();
		
		if (uri.endsWith("/login.jsf")) {
			chain.doFilter(req, res);
			return;
		}
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsf");
		} else {
			if (controleAcesso.isAdministrador() || controleAcesso.telaAcessoPublico(uri) || controleAcesso.isAutorizado(uri)) {
				chain.doFilter(req, res);
			} else {
				response.sendRedirect(request.getContextPath() + "/pages/home/acessoNaoAutorizado.jsf");	
			}
		}
		
		context.release();
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}