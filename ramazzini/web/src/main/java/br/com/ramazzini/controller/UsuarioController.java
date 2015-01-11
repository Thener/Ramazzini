package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.service.PerfilService;
import br.com.ramazzini.service.UsuarioService;
import br.com.ramazzini.util.Md5;

@Named
@ConversationScoped
public class UsuarioController extends AbstractBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private @Inject Conversation conversation;

	@Inject
    private FacesContext facesContext; 
    
    @Inject
    private UsuarioService usuarioService;  
    
    @Inject
	private PerfilService perfilService;
    
    private Usuario usuarioNovo;    
    private Usuario usuarioSelecionado;    
    private List<Usuario> usuarios;
    
    private Perfil perfilSelecionado;
    private List<Perfil> perfisDisponiveis;
    private List<Perfil> perfisUsuario;
    
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
    	if (conversation.isTransient()) {
			conversation.begin();
		}
    }
    
    
    public Usuario getNovoUsuario() {
        return usuarioNovo;
    }
  
    public String salvar() throws Exception {
        try {
        	usuarioNovo.setSenha(Md5.hashMd5(usuarioNovo.getSenha())); 
        	perfisUsuario.add(perfilSelecionado);
        	perfisDisponiveis.remove(perfilSelecionado);
        	usuarioNovo.setPerfis(perfisUsuario);
            usuarioService.salvar(usuarioNovo);
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuario salvo com sucesso!"));            
            String alterar = editar(usuarioNovo);
            init();
            return alterar;
        } catch (Exception e) {
            String errorMessage = getRaizErro(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Usuario não salvo!");
            init();
            facesContext.addMessage(null, m);       
            return null;
        }
    }  
    public void atualizar() throws Exception {
        try {
        	if (!novaSenha.isEmpty()){
        		usuarioSelecionado.setSenha(Md5.hashMd5(novaSenha));
        	}
            usuarioService.salvar(usuarioSelecionado);
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuario alterado com sucesso!"));
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
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuario removido com sucesso!"));
    	} catch (Exception e) {
            String errorMessage = getRaizErro(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Não foi pssível deletar o Usuario!");
            facesContext.addMessage(null, m);            
        }            
    }
    
    public void removerPerfilUsuario(Perfil perfil){
    	try {
    		perfisUsuario.remove(perfil);
    		perfisDisponiveis.add(perfil);
    		usuarioSelecionado.setPerfis(perfisUsuario);
    		atualizar();
    		facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuario removido com sucesso!"));
    	} catch (Exception e) {
            String errorMessage = getRaizErro(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Não foi pssível deletar o Usuario!");
            facesContext.addMessage(null, m);            
        }            
    }
    public String editar(Usuario usuario){
    	setUsuarioSelecionado(usuarioService.recuperarPorId(usuario.getId()));
    	setSomenteLeitura(Boolean.FALSE);    	
    	perfisDisponiveis = perfilService.recuperarPerfisDisponiveisPorUsuario(usuario);
    	perfisUsuario = perfilService.recuperarTudoPorUsuario(usuario);
    	return "alterarUsuario.jsf?faces-redirect=true";
    }
    
    public String incluirUsuario(){
    	perfisDisponiveis = perfilService.recuperarTodosMenosAdmin();
    	perfisUsuario = new ArrayList<Perfil>();
    	return "incluirUsuario.jsf?faces-redirect=true";
    }
    
    public String visualizar(Usuario usuario){
    	setUsuarioSelecionado(usuario);
    	setSomenteLeitura(Boolean.TRUE);
    	perfisDisponiveis = perfilService.recuperarPerfisDisponiveisPorUsuario(usuario);
    	perfisUsuario = perfilService.recuperarTudoPorUsuario(usuario);
    	return "alterarUsuario.js?faces-redirect=truef";
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
    public void adicionarPerfil(){
    	perfisUsuario.add(perfilSelecionado);
    	usuarioSelecionado.setPerfis(perfisUsuario);
    	perfisDisponiveis.remove(perfilSelecionado);
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

	public Perfil getPerfilSelecionado() {
		return perfilSelecionado;
	}

	public void setPerfilSelecionado(Perfil perfilSelecionado) {
		this.perfilSelecionado = perfilSelecionado;
	}

	public List<Perfil> getPerfisDisponiveis() {
		return perfisDisponiveis;
	}

	public void setPerfisDisponiveis(List<Perfil> perfis) {
		this.perfisDisponiveis = perfis;
	}

	public Collection<Perfil> getPerfisUsuario() {
		return perfisUsuario;
	}

	public void setPerfisUsuario(List<Perfil> perfisUsuario) {
		this.perfisUsuario = perfisUsuario;
	}	
}
