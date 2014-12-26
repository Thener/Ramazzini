package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.service.UsuarioService;

@Named
@SessionScoped
public class UsuarioController implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private FacesContext facesContext; 
    
    @Inject
    private UsuarioService usuarioService;   
    
    private Usuario usuarioNovo;
    
    @Inject @Named("usuarioLogado") 
    private Usuario usuarioLogado;
    
    private List<Usuario> usuarios;
    
    private String loginPesquisa;
    private String gridMsg;
    
	@Produces
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
   
    @PostConstruct     
    public void init() {
    	usuarios = new ArrayList<Usuario>();
    	usuarioNovo = new Usuario();  
    	gridMsg = "";
    }
    
    @Produces
    public Usuario getNovoUsuario() {
        return usuarioNovo;
    }
  
    public void salvar() throws Exception {
        try {
            usuarioService.salvar(usuarioNovo, usuarioLogado);
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado!", "Usuario cadastrado com sucesso!"));
            init();
        } catch (Exception e) {
            String errorMessage = getRaizErro(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Usuario não cadastrado!");
            init();
            facesContext.addMessage(null, m);            
        }
    }  
    public void pesquisar() throws Exception {
		if (loginPesquisa.isEmpty()){
			usuarios = usuarioService.recuperarTodos("nome");        		
		} else {
			List<Usuario> usuariosRecuperados = usuarioService.recuperarPorTrechoLogin(loginPesquisa);
			if (!usuariosRecuperados.isEmpty()) {
				usuarios=usuariosRecuperados;
			} else {
				init();
				gridMsg = "Login informado não cadastrado.";
			}
		}      
    }  
    
    private String getRaizErro(Exception e) {
        String errorMessage = "Registro falhou. Veja o log do servidor para mais informações.";
        if (e == null) {
            // Se não houver uma Exception, retorna a mensagem padrão
            return errorMessage;
        }
        Throwable t = e;
        while (t != null) {
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        return errorMessage;
    }

	public String getLoginPesquisa() {
		return loginPesquisa;
	}

	public void setLoginPesquisa(String loginPesquisa) {
		this.loginPesquisa = loginPesquisa;
	} 

	public String getGridMsg() {
		return gridMsg;
	}

	public void setGridMsg(String gridMsg) {
		this.gridMsg = gridMsg;
	}
}
