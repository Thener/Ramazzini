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
import br.com.ramazzini.util.Md5;

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
    
    private Usuario usuarioSelecionado;
    
    private List<Usuario> usuarios;
    
    private String loginPesquisa;
    private String gridMsg;
    private String novaSenha;

    
    private boolean somenteLeitura = Boolean.FALSE;
    
	
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
   
    @PostConstruct     
    public void init() {
    	usuarios = new ArrayList<Usuario>();
    	usuarioNovo = new Usuario(); 
    	gridMsg = "";
    	somenteLeitura = Boolean.FALSE; 
    }
    
    
    public Usuario getNovoUsuario() {
        return usuarioNovo;
    }
  
    public void salvar() throws Exception {
        try {
        	usuarioNovo.setSenha(Md5.hashMd5(usuarioNovo.getSenha()));
            usuarioService.salvar(usuarioNovo);
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo!", "Usuario salvo com sucesso!"));
            init();
        } catch (Exception e) {
            String errorMessage = getRaizErro(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Usuario não salvo!");
            init();
            facesContext.addMessage(null, m);            
        }
    }  
    public void atualizar() throws Exception {
        try {
        	if (!novaSenha.isEmpty()){
        		usuarioSelecionado.setSenha(Md5.hashMd5(novaSenha));
        	}
            usuarioService.salvar(usuarioSelecionado);
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado!", "Usuario alteraddo com sucesso!"));
            init();
        } catch (Exception e) {
            String errorMessage = getRaizErro(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Usuario não alterardo!");
            init();
            facesContext.addMessage(null, m);            
        }
    }  
    public void remover(Usuario usuario){
    	try {
    		usuarioService.remover(usuario, usuario.getId());
    		usuarios.remove(usuario);
    		facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuario removido com sucesso!"));
    	} catch (Exception e) {
            String errorMessage = getRaizErro(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Não foi pssível deletar o Usuario!");
            facesContext.addMessage(null, m);            
        }            
    }
    public String editar(Usuario usuario){
    	setUsuarioSelecionado(usuarioService.recuperarPorId(usuario.getId()));
    	setSomenteLeitura(Boolean.FALSE);
    	return "alterarUsuario.jsf";
    }
    
    public String visualizar(Usuario usuario){
    	setUsuarioSelecionado(usuario);
    	setSomenteLeitura(Boolean.TRUE);
    	return "alterarUsuario.jsf";
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

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}	
}
